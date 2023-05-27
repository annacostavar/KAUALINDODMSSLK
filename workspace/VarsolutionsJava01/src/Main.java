import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        /*
            System.out.println("ola mundo este é o meu primeiro codigo em JAVA");
            System.out.println("este é o meu primerio codigo em JAVA AAAAAAAAEAEE");

//       1                   2                             3
// tipo da variavel  nome da variavel   valor da variavel

        String olaMundo = "Olá esta variavel esta dentro da variavel";

        System.out.println(olaMundo);

        String nome = "vitor";
        String sobrenome = " ferreira";

        System.out.println(nome);
        System.out.println(sobrenome);

        //juntar as variaveis:
        System.out.println(nome + " " + sobrenome + ".");

        double imc = calcularIMC();
        System.out.println("imc " + imc);


        int anoNascimento = 2006;
        int idade = retornoIdadePorAno(anoNascimento);

        System.out.println("Sua idade é : " + idade);
*/

        List<String> retornoMensagem = resumoPessoa();
        System.out.println(retornoMensagem);

    }
    //     1                   2                    3                       4
    //tipo acesso        tipo de retorno    nome do metodo            parâmetros
        public static String buscarSobrenome(){
           String sobrenome = "ferreira";
           return sobrenome;
        }

        public static String inserirnome(){
        Scanner ler = new Scanner(System.in);
        System.out.printf("Digite seu nome:");
        String nome = ler.next();

        System.out.printf("Digite seu sobrenome:");
        String sobrenome = ler.next();

            System.out.println(nome + " " + sobrenome);

            String nomeCompleto = nome + " " + sobrenome;

        return nomeCompleto;
        }

        public static double calcularIMC(){

        Scanner ler = new Scanner(System.in);

            System.out.printf("Digite sua altura: ");
            double altura = ler.nextDouble();

            System.out.printf("Digite seu peso: ");
            double peso = ler.nextDouble();

            double calculoIMC = peso / (altura * altura);

            System.out.println("O seu imc é: " + calculoIMC);

            if(calculoIMC < 19.0){
                System.out.println("voce esta abaixo do peso!");
            }
            else{
                System.out.println("Você esta acima do peso!");
            }

            return calculoIMC;
        }

        public static int retornoIdadePorAno(){
                   int idadeCalculada;

                   Scanner ler = new Scanner(System.in);

                   System.out.printf("Digite seu nome: ");
                   String nome = ler.next();

                   System.out.printf("Digite o seu ano de nascimento, Ex : 1990: ");
                   int anoBase = ler.nextInt();

                   idadeCalculada = 2023 - anoBase;

                   String tipoDePessoa;

                   String maiorDeDezoito = "Você é maior de 18 anos";

                   String menorDeDezoito =  "Você é menor de 18 anos, cai fora!!!";

                   if(idadeCalculada <= 17){
                       tipoDePessoa = menorDeDezoito;
                   }
                   else
                   {

                        tipoDePessoa = maiorDeDezoito;
                   }
            System.out.println(nome + "Sua idae é " + idadeCalculada + " anos e " + tipoDePessoa);
                   return idadeCalculada;
        }
       public static List<String> resumoPessoa(){
           Scanner ler = new Scanner(System.in);

           List<String> nomesResumo = new ArrayList<>();

          //         1        ;     2     ;    3
           for (int indice = 1; indice < 4; indice ++){

               //Pergunta ao usuario nome da pessoa
               System.out.printf("Digite seu nome:");
               String nome = ler.next();

               //Pergunta a usuario e sobrenome da Pessoa
               System.out.printf("Digite seu sobrenome:");
               String sobrenome = ler.next();

               //Pergunta ao usuario sua idade da pessoa
               System.out.printf("Digite sua idade:");
               int idade = ler.nextInt();

               // criar os nomes juntos nome + sobrenome
               String nomeCompleto = nome + " " + sobrenome;

               //verificar se idade é menor ou igual a 17 anos.
               String tipoDePessoa = " ";
               if(idade <= 17){
                   // se for menor ou igual a 17 anos atribui menor de idade na mensagem
                   tipoDePessoa = "menor de idade";
               }
               else
               {
                   // se for maior do que 17 anos
                   tipoDePessoa = "maior de idade";
               }

               String resumo = " O nome completo é: " + nomeCompleto + ", ele é: " + tipoDePessoa;

               nomesResumo.add(resumo);

           }


        return nomesResumo;
       }


    }
