package br.com.raca.gatos.controller;

import br.com.raca.gatos.controller.api.RacaApi;
import br.com.raca.gatos.model.RacaDto;
import br.com.raca.gatos.service.RacaService;
import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("racas")
public class RacaController implements RacaApi {

    @Autowired
    public RacaController(RacaService racaService) {
        this.racaService = racaService;
    }

    private RacaService racaService;

    @GetMapping
    public ResponseEntity<List<RacaDto>> buscarRacas() {
        return new ResponseEntity<>(racaService.buscarRacas(), HttpStatus.OK);
    }

    @GetMapping("origem/{origem}")
    public ResponseEntity<List<RacaDto>> buscarRacasPorOrigem(@PathVariable String origem) {
        return new ResponseEntity<>(racaService.buscarRacaPorOrigem(origem), HttpStatus.OK);
    }

    @GetMapping("temperamento")
    public ResponseEntity<List<RacaDto>> buscarRacas(@RequestParam String temperamento) {
        return new ResponseEntity<>(racaService.buscarRacasPorTemperamento(temperamento), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RacaDto> buscarRacaPorId(@PathVariable(name = "id") String racaId){
        return new ResponseEntity<>(racaService.buscarRacaPorId(racaId), HttpStatus.OK);
    }

    @GetMapping("id-externo/{idExterno}")
    public ResponseEntity<RacaDto> buscarRacaPorIdExterno(@PathVariable String idExterno){
        return new ResponseEntity<>(racaService.buscarRacaPorIdExterno(idExterno), HttpStatus.OK);
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<List<RacaDto>> buscarRacaPorNome(@PathVariable String nome){
        return new ResponseEntity<>(racaService.buscarRacaPorNome(nome), HttpStatus.OK);
    }

}
