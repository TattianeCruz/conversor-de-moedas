import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.text.DecimalFormat;
import java.util.Scanner;

import java.net.http.HttpRequest;

import java.net.http.HttpResponse;
import java.util.Scanner;

public class conversor {
    public static void main (String[] args)  {

        int escolha = 0;
        Scanner valorScn = new Scanner (System.in);
        while (escolha !=7 ) {
            escolha = printmenu();
            if (escolha == 7){
                continue;
            }
            System.out.println("Digite o Valor que deseja converter:");
            float valor = valorScn.nextFloat();
            switch (escolha) {
                case 1:
                    try {
                       System.out.println ("valor "+valor +"[ARS] correspponde ao valor de =>>> "+ funcao("ARS",valor)+" Dolár") ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        System.out.println ("valor "+valor +"[BOB] correspponde ao valor de =>>> "+ funcao("BOB",valor)+" Dolár") ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    case 3:
                    try {
                        System.out.println ("valor "+valor +"[BRL] correspponde ao valor de =>>> "+ funcao("BRL",valor)+" Dolár") ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    case 4:
                    try {
                        System.out.println ("valor "+valor +"[CLP] correspponde ao valor de =>>> "+ funcao("CLP",valor)+" Dolár") ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    case 5:
                    try {
                        System.out.println ("valor "+valor +"[COP] correspponde ao valor de =>>> "+ funcao("COP",valor)+" Dolár") ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    case 6:
                    try {
                        System.out.println ("valor "+valor +"[MXN] correspponde ao valor de =>>> "+ funcao("MXN",valor)+" Dolár") ;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    default:
                        System.out.println("Opção inválida");
            }

        }
    }

    public static int printmenu (){

        Scanner escolhaScn = new Scanner (System.in);
        System.out.println("*****************************************************");
        System.out.println("Seja Bem Vindo ao Conversor de Moedas =]\n");
        System.out.println("1) Peso argentino ===> Dólar ");
        System.out.println("2) Boliviano boliviano ===> Dólar ");
        System.out.println("3) Real brasileiro===> Dólar ");
        System.out.println("4) Peso chileno ===> Dólar ");
        System.out.println("5) Peso colombiano ===> Dólar ");
        System.out.println("6) Peso Mexicano ===> Dólar ");
        System.out.println("7) Sair");
        System.out.println("Escolha uma opção válida:\n");
        System.out.println("****************************************************\n");
        int escolha = escolhaScn.nextInt();

      return escolha;



    }

    public static String funcao (String codigo_moeda,float valor) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/d094fd7644591335eee83157/latest/USD"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonElement json = JsonParser.parseString(response.body());

        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject currency = jsonObject.get("conversion_rates").getAsJsonObject();


       float moeda = Float.parseFloat(currency.get(codigo_moeda).getAsString());
       String result = new DecimalFormat("###.##").format(valor/moeda);
       return result;
    }


}

