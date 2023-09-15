package com.mfarias25.rh.controller;

//Controlador recebe uma req e processa e retorna uma reposta para req.

import com.mfarias25.rh.model.Funcionario;
import com.mfarias25.rh.repository.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Gerencia a class pelo spring
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @GetMapping //Mapea o GET metodo testar()
    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();

    }

    @RequestMapping("/{funcionarioId}")
    public ResponseEntity<Funcionario> buscarById(@PathVariable Long funcionarioId) {
        return funcionarioRepository.findById(funcionarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario cadastrar(@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{funcionarioId}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long funcionarioId,
                                                 @RequestBody Funcionario funcionario) {
        if (!funcionarioRepository.existsById(funcionarioId)){
            return ResponseEntity.notFound().build();
        }

    funcionario.setId(funcionarioId);
    Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);
    return ResponseEntity.ok(funcionarioAtualizado);
        }

}
