package com.kazale.pontointeligente.service.impl

import com.kazale.pontointeligente.documents.Funcionario
import com.kazale.pontointeligente.repository.FuncionarioRepository
import com.kazale.pontointeligente.service.FuncionarioService
import org.springframework.stereotype.Service
import java.util.*

@Service
class FuncionarioServiceImpl(val funcionarioRepository: FuncionarioRepository) : FuncionarioService {

    override fun persistir(funcionario: Funcionario): Funcionario =
        funcionarioRepository.save(funcionario)

    override fun buscarPorCpf(cpf: String): Funcionario? =
        funcionarioRepository.findByCpf(cpf)

    override fun buscarPorEmail(email: String): Funcionario? =
        funcionarioRepository.findByEmail(email)

    override fun buscarPorId(id: String): Funcionario? =
        funcionarioRepository.findById(id).orElse(null)
}