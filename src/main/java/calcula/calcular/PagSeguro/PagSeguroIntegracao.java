package calcula.calcular.PagSeguro;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class PagSeguroIntegracao {
	private static String urlAPI = "https://ws.sandbox.pagseguro.uol.com.br/v2/checkout";
	private static String email = "vitor.tomasi@hotmail.com";
	private static String token = "87EB0427AEFB496386D592DDB72AB7EC";
	private static String urlRet = "https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=";

	public static String gerarLinkPagamento(double valor, String nome)
			throws URISyntaxException, IOException, InterruptedException {

		DecimalFormatSymbols formatoLocal = new DecimalFormatSymbols(Locale.US);
		DecimalFormat format = new DecimalFormat("#.00", formatoLocal);
		String valorFormatado = format.format(valor);
		String retornoCode = "";
		String xml = "<checkout><sender><name>Vitor Tomasi</name><email>vitor.tomasi@hotmail.com</email><phone><areaCode>55</areaCode><number>999999999</number></phone><documents><document><type>CPF</type><value>88483320053</value></document></documents></sender><currency>BRL</currency><items><item><id>1</id><description>Pagamento do "
				+ nome + "</description><amount>" + valorFormatado
				+ "</amount><quantity>1</quantity><weight>1</weight><shippingCost>0.00</shippingCost></item></items><redirectURL>http://lojamodelo.com.br/return.html</redirectURL><extraAmount>0.00</extraAmount><reference>REF1234</reference><shipping><address><street>Av. PagSeguro</street><number>9999</number><complement>99o andar</complement><district>Jardim Internet</district><city>Cidade Exemplo</city><state>SP</state><country>BRA</country><postalCode>99999999</postalCode></address><type>1</type><cost>0.00</cost><addressRequired>true</addressRequired></shipping><timeout>25</timeout><maxAge>999999999</maxAge><maxUses>999</maxUses><receiver><email>vitor.tomasi@hotmail.com</email></receiver><enableRecover>false</enableRecover></checkout>";

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(new URI(urlAPI + "?email=" + email + "&token=" + token))
				.header("Content-Type", "application/xml; charset=ISO-8859-1")
				.POST(HttpRequest.BodyPublishers.ofString(xml)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		retornoCode = retornaCode(response.body());

		return !"".equals(retornoCode) ? (urlRet + retornaCode(response.body())) : "";
	}

	private static String retornaCode(String xml) {

		Document document = Jsoup.parse(xml, "", Parser.xmlParser());

		Element codeElement = document.selectFirst("code");
		if (codeElement != null) {
			return codeElement.text();
		} else {
			return "";
		}
	}

}
