package br.com.villaca.arte.util.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Operações Genéricas", description = "CRUD genérico para todas as entidades do sistema.")
public class Controller<
        PK,
        SERVICE extends Service<PK, RESPONSE, REQUEST>,
        RESPONSE,
        REQUEST> {

    @Autowired
    protected SERVICE service;

    @Operation(
            summary = "Listar todos os registros",
            description = "Retorna uma lista completa de registros da entidade, sem aplicar filtros ou paginação."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping
    public ResponseEntity<List<RESPONSE>> findAll() {
        return ResponseEntity.ok((List<RESPONSE>) service.findAll());
    }

    @Operation(
            summary = "Listar registros com paginação",
            description = "Retorna registros paginados, permitindo controlar página e quantidade por página."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista paginada retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/paginated")
    public ResponseEntity<Page<RESPONSE>> findAllPaginated(
            @Parameter(description = "Número da página (iniciando em 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Quantidade de registros por página", example = "15")
            @RequestParam(defaultValue = "15") int size
    ) {
        return ResponseEntity.ok(service.findAllPaginated(page, size));
    }

    @Operation(
            summary = "Buscar por ID",
            description = "Retorna um registro específico a partir do identificador informado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RESPONSE> findById(
            @Parameter(description = "Identificador único do registro", required = true)
            @PathVariable PK id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Criar novo registro",
            description = "Cria um novo registro na base de dados a partir das informações fornecidas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PostMapping
    public ResponseEntity<RESPONSE> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto de requisição com os dados a serem criados",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Object.class))
            )
            @RequestBody REQUEST novo
    ) {
        return new ResponseEntity<>(service.create(novo), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Deletar registro",
            description = "Remove permanentemente o registro identificado pelo ID informado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identificador único do registro", required = true)
            @PathVariable PK id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Atualizar registro",
            description = "Atualiza um registro existente com as novas informações fornecidas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RESPONSE> update(
            @Parameter(description = "Identificador do registro a ser atualizado", required = true)
            @PathVariable PK id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto com os novos dados do registro",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Object.class))
            )
            @RequestBody REQUEST novo
    ) {
        RESPONSE atualizado = service.update(id, novo);
        return ResponseEntity.ok(atualizado);
    }
}
