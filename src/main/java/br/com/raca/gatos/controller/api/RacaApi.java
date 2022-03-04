package br.com.raca.gatos.controller.api;

import br.com.raca.gatos.entity.Raca;
import br.com.raca.gatos.model.RacaDto;
import br.com.raca.gatos.model.rest.FalhaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RacaApi {

    @Operation(description = "Busca todas as raças")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Retornou a consulta de raças com sucesso", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Raca.class)))}),
            @ApiResponse(responseCode = "500", description = "Erro inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))})})
    ResponseEntity<List<RacaDto>> buscarRacas();

    @Operation(description = "Busca raça de acordo com o país de origem")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Retornou a consulta de raças com sucesso", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Raca.class)))}),
            @ApiResponse(responseCode = "400", description = "Erro nos parametros da requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))})})
    ResponseEntity<List<RacaDto>> buscarRacasPorOrigem(@PathVariable String origem);

    @Operation(description = "Busca raça de acordo com o temperamento")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Retornou a consulta de raças com sucesso", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Raca.class)))}),
            @ApiResponse(responseCode = "400", description = "Erro nos parametros da requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))})})
    ResponseEntity<List<RacaDto>> buscarRacas(@RequestParam String temperamento);

    @Operation(description = "Busca raça pelo id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Retornou a consulta de raças com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RacaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Erro nos parametros da requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))})})
    ResponseEntity<RacaDto> buscarRacaPorId(@PathVariable(name = "id") String racaId);

    @Operation(description = "Busca raça pelo id externo")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Retornou a consulta de raças com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Raca.class))}),
            @ApiResponse(responseCode = "400", description = "Erro nos parametros da requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))})})
    ResponseEntity<RacaDto> buscarRacaPorIdExterno(@PathVariable String idExterno);

    @Operation(description = "Busca raças pelo nome")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Retornou a consulta de raça com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Raca.class))}),
            @ApiResponse(responseCode = "400", description = "Erro nos parametros da requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FalhaResponse.class))})})
    ResponseEntity<List<RacaDto>> buscarRacaPorNome(@PathVariable String nome);
}
