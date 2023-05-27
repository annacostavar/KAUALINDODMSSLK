package com.br.var.solutions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "*")
@Slf4j
public class PessoaController {
    //         1          2                          3                4
    // publico || privado //tipo de retorno // nome de metodo // parãmetros

    //Endpoint

    @GetMapping
    public ResponseEntity<Object> get() {
        PessoaRequest pessoaRequest1 = new PessoaRequest();
        pessoaRequest1.setNome("kaua");
        pessoaRequest1.setSobrenome("ferreria");
        pessoaRequest1.setEndereco("Avenida CASTELO");
        pessoaRequest1.setIdade(17);
        return ResponseEntity.ok(pessoaRequest1);

    }

    @GetMapping("/resumo")
//                                                   tipo/objeto    apelido
    public ResponseEntity<Object> getPessoa(@RequestBody PessoaRequest pessoinha, @RequestParam(value = "valida_mundial")
    boolean DesejaValidarmundial) {
        InformacoesImc imc = new InformacoesImc();
        int anoNascimento = 0;
        String impostoRenda = null;
        String validaMundial = null;
        String saldoEmDolar = null;

        if (!pessoinha.getNome().isEmpty()) {

            log.info("iniciando o processo de resumo da pessoa:", pessoinha);

            if (Objects.nonNull(pessoinha.getPeso()) && Objects.nonNull(pessoinha.getAltura())) {
                log.info("iniciando o calculo do IMC");
                imc = calcularImc(pessoinha.getPeso(), pessoinha.getAltura());
            }

            if (Objects.nonNull(pessoinha.getIdade())) {
                log.info("Iniciando calculo do ano de nascimento");
                anoNascimento = calculaAnoNascimento(pessoinha.getIdade());
            }

            if (Objects.nonNull((pessoinha.getSalario()))) {
                log.info("Iniciando calculo do imposto de renda");
                impostoRenda = calculaFaixaImpostoRenda(pessoinha.getSalario());
            }
            if(Boolean.TRUE.equals(DesejaValidarmundial)) {
                if (Objects.nonNull(pessoinha.getTime())) {

                    log.info("Validando s e o time de coração tem mundial");
                    validaMundial = calcularMundial(pessoinha.getTime());
                }
            }
            if (Objects.nonNull(pessoinha.getSaldo())) {

                log.info("convertendo real em Dolar");
                saldoEmDolar = converteSaldoDolar(pessoinha.getSaldo());
            }

            log.info("montando objeto de retorno para o front-end");
            PessoaResponse resumo = complementarRespostaFrontEnd(pessoinha, imc, anoNascimento, impostoRenda, validaMundial, saldoEmDolar);

            return ResponseEntity.ok(resumo);
        }
        return ResponseEntity.noContent().build();
    }

    private String converteSaldoDolar(double saldo) {
        return String.valueOf(saldo / 5.11);
    }

    private PessoaResponse complementarRespostaFrontEnd(PessoaRequest pessoa, InformacoesImc imc, int anoNascimento,
                                                        String impostoRenda, String validaMundial, String saldoEmDolar) {
        PessoaResponse response = new PessoaResponse();

        response.setNome(pessoa.getNome());
        response.setImc(imc.getImc());
        response.setClassificacao(imc.getClassificacao());
        response.setSalario(impostoRenda);
        response.setAnoNascimento(anoNascimento);
        response.setMundialClubes(validaMundial);
        response.setSaldoEmDolar(saldoEmDolar);
        response.setIdade(pessoa.getIdade());
        response.setTime(pessoa.getTime());
        response.setSobrenome(pessoa.getSobrenome());
        response.setAltura(pessoa.getAltura());
        response.setPeso(pessoa.getPeso());
        response.setSaldo(pessoa.getSaldo());
        return response;
    }


    private String calcularMundial(String time) {
        if (time.equalsIgnoreCase("Corinthians")) {
            return "Parabéns, o seu time possui 2 mundiais de clubes conforme a FIFA";
        } else if (time.equalsIgnoreCase("São Paulo")) {
            return "Parabéns, o seu time possui 3 mundiais de clubes conforme a FIFA";
        } else if (time.equalsIgnoreCase("Santos")) {
            return "Parabéns, o seu time possui 2 mundiais de clubes conforme a FIFA";
        } else {
            return "Poxa, continue torcendo para o seu clube ganhar um mundial";
        }

    }

// Regra : Base de calculo é salario Bruto X Aliquota - dedução;

    private String calculaFaixaImpostoRenda(double salario) {
        log.info("Inicaindo o calculo do imposto de renda:" + salario);
        String novoSalarioCalculado;

        if (salario <= 1903.98) {
            return "isento";

        } else if (salario > 1903.98 && salario < 2826.65) {
            double calculoIRF = 142.80 - ((salario * 0.075) / 100);
            double novoSalario = salario - calculoIRF;
            novoSalarioCalculado = String.valueOf(novoSalario);

            return novoSalarioCalculado;

        } else if (salario >= 2826.66 && salario < 3075.05) {
            double calculoIRF = 354.80 - ((salario * 0.15) / 100);
            double novoSalario = salario - calculoIRF;
            novoSalarioCalculado = String.valueOf(novoSalario);

            return novoSalarioCalculado;

        } else if (salario >= 3751.06 && salario < 4664.68) {
            double calculoIRF = 636.13 - ((salario * 0.225) / 100);
            double novoSalario = salario - calculoIRF;

            novoSalarioCalculado = String.valueOf(novoSalario);
            return novoSalarioCalculado;
        } else {
            double calculoIRF = 869.36 - ((salario * 225) / 100);
            double novoSalario = salario - calculoIRF;
            novoSalarioCalculado = String.valueOf(novoSalario);

            return novoSalarioCalculado;
        }
    }

    private int calculaAnoNascimento(int idade) {
        LocalDate datalocal = LocalDate.now();
        int anoAtual = datalocal.getYear();
        return anoAtual - idade;

    }

    private InformacoesImc calcularImc(double peso, double altura) {
        double imc = peso / (altura * altura);

        InformacoesImc imcCalculado = new InformacoesImc();


        if (imc <= 18.5) {
            imcCalculado.setImc(String.valueOf(imc));
            imcCalculado.setClassificacao("abaixo do peso.");
            return imcCalculado;

        } else if (imc >= 18.5 && imc <= 24.9) {
            imcCalculado.setImc(String.valueOf(imc));
            imcCalculado.setClassificacao("peso ideal");
            return imcCalculado;

        } else if (imc > 24.9 && imc <= 29.9) {
            imcCalculado.setImc(String.valueOf(imc));
            imcCalculado.setClassificacao("exesso de peso.");
            return imcCalculado;

        } else if (imc > 29.9 && imc <= 34.9) {
            imcCalculado.setImc(String.valueOf(imc));
            imcCalculado.setClassificacao("obesidade classe I");
            return imcCalculado;

        } else if (imc > 34.9 && imc <= 39.9) {
            imcCalculado.setImc(String.valueOf(imc));
            imcCalculado.setClassificacao("obesidade classe II");
            return imcCalculado;

        } else {
            imcCalculado.setImc(String.valueOf(imc));
            imcCalculado.setClassificacao("obesidade classe III");
            return imcCalculado;
        }
    }
}
