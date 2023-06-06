package calcula.calcular.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import calcula.calcular.JSON.JSONCalcular;
import calcula.calcular.JSON.JSONRetorno;
import calcula.calcular.process.ProcessamentoCalcular;

@RestController
@CrossOrigin("*")
public class ControllerApp {
	
	@PostMapping(value="/calcular", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<JSONRetorno> ReqCalcular(@RequestBody JSONCalcular json) throws IOException {
		return ProcessamentoCalcular.Calcular(json);
	}

}
