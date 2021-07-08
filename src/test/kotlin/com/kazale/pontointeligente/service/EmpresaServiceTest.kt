package com.kazale.pontointeligente.service

import com.kazale.pontointeligente.documents.Empresa
import com.kazale.pontointeligente.repository.EmpresaRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureDataMongo
class EmpresaServiceTest {

    @Autowired
    val empresaService: EmpresaService? = null

    @MockBean
    private val empresaRepository: EmpresaRepository? = null

    private val CNPJ = "51463645000100"

    @BeforeEach
    @Throws(Exception::class)
    fun setup() {
        BDDMockito.given(empresaRepository?.findByCnpj(CNPJ)).willReturn(empresa())
        BDDMockito.given(empresaRepository?.save(empresa())).willReturn(empresa())
    }

    @Test
    fun testBuscarEmpresaPorCnpj() {
        val empresa: Empresa? = empresaService?.buscarPorCnpj(CNPJ)
        assertNotNull(empresa)
    }

    @Test
    fun testPersistirEmpresa() {
        val empresa: Empresa? = empresaService?.persistir(empresa())
        assertNotNull(empresa)
    }

    private fun empresa(): Empresa = Empresa("Razão Social", CNPJ, "1")
}