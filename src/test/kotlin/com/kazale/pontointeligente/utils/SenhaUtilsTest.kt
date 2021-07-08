package com.kazale.pontointeligente.utils

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtilsTest {

    private val SENHA = "123456"
    private val bCryptEncoder = BCryptPasswordEncoder()

    @Test
    fun testGerarHashSenha() {
        val hash = SenhaUtils().gerarBCrypt(SENHA)
        assertTrue(bCryptEncoder.matches(SENHA, hash))
    }
}