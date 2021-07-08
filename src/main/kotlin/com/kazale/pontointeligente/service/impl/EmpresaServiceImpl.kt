package com.kazale.pontointeligente.service.impl

import com.kazale.pontointeligente.documents.Empresa
import com.kazale.pontointeligente.repository.EmpresaRepository
import com.kazale.pontointeligente.service.EmpresaService
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)
}