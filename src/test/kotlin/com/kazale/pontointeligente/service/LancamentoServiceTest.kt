package com.kazale.pontointeligente.service

import com.kazale.pontointeligente.documents.Lancamento
import com.kazale.pontointeligente.enums.TipoEnum
import com.kazale.pontointeligente.repository.LancamentoRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*
import kotlin.collections.ArrayList
import kotlin.jvm.Throws

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureDataMongo
class LancamentoServiceTest {

    @MockBean
    private val lancamentoRepository: LancamentoRepository? = null

    @Autowired
    private val lancamentoService: LancamentoService? = null

    private val id: String = "1"

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given<Page<Lancamento>>(lancamentoRepository?.findByFuncionarioId(id, PageRequest.of(0, 10)))
            .willReturn(PageImpl(ArrayList<Lancamento>()))
        BDDMockito.given(lancamentoRepository?.findById("1")).willReturn(Optional.of(lancamento()))
        BDDMockito.given(lancamentoRepository?.save(Mockito.any(Lancamento::class.java))).willReturn(lancamento())
    }

    @Test
    fun testBuscarLancamentoPorFuncionarioId() {
        val lancamento = lancamentoService?.buscarPorFuncionarioId(id, PageRequest.of(0, 10))
        assertNotNull(lancamento)
    }

    @Test
    fun testBuscarLancamentoPorId() {
        val lancamento = lancamentoService?.buscarPorId(id)
        assertNotNull(lancamento)
    }

    @Test
    fun testPersistirLancamento() {
        val lancamento = lancamentoService?.persistir(lancamento())
        assertNotNull(lancamento)
    }

    private fun lancamento(): Lancamento = Lancamento(Date(), TipoEnum.INICIO_TRABALHO, id)
}