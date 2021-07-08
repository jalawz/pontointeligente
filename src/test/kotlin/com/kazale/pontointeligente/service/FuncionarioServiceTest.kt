package com.kazale.pontointeligente.service

import com.kazale.pontointeligente.documents.Funcionario
import com.kazale.pontointeligente.enums.PerfilEnum
import com.kazale.pontointeligente.repository.FuncionarioRepository
import com.kazale.pontointeligente.utils.SenhaUtils
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.lang.Exception
import java.util.*
import kotlin.jvm.Throws

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureDataMongo
class FuncionarioServiceTest {

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    @Autowired
    private val funcionarioService: FuncionarioService? = null

    private val email: String = "email@email.com"
    private val cpf: String = "23526236236236"
    private val id: String = "1"

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(funcionarioRepository?.save(any(Funcionario::class.java))).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findById(id)).willReturn(Optional.of(funcionario()))
        BDDMockito.given(funcionarioRepository?.findByEmail(email)).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findByCpf(cpf)).willReturn(funcionario())
    }

    @Test
    fun testPersistirFuncionario() {
        val funcionario = this.funcionarioService?.persistir(funcionario())
        assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorCpf() {
        val funcionario = this.funcionarioService?.buscarPorCpf(cpf)
        assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorEmail() {
        val funcionario = this.funcionarioService?.buscarPorEmail(email)
        assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorId() {
        val funcionario = this.funcionarioService?.buscarPorId(id)
        assertNotNull(funcionario)
    }

    private fun funcionario(): Funcionario = Funcionario("Paulo", email,
        SenhaUtils().gerarBCrypt("123456"), cpf, PerfilEnum.ROLE_ADMIN, "1",
        50.0, 8f, 1f, id)
}