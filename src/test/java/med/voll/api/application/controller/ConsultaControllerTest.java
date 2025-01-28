package med.voll.api.application.controller;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import med.voll.api.application.domain.DadosConsultaDTO;
import med.voll.api.application.service.AgendaDeConsultasService;
import med.voll.api.domain.Especialidades;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosConsultaDTO> jsonConsulta;

    @Autowired
    private JacksonTester<DadosConsultaDTO> jsonResposta;

    @MockitoBean
    private AgendaDeConsultasService agenda;

    @Test
    @DisplayName("Teste de cadastro de consulta com sucesso.")
    @WithMockUser
    public void testeAgendamentoDeConsultaComSucesso() throws Exception {
        
        var data = LocalDateTime.now().plusHours(1);
        var dadosRetorno = new DadosConsultaDTO(1l, 1l, 1l, data, Especialidades.DERMATOLOGIA);

        when(agenda.agendar(any())).thenReturn(dadosRetorno);
        
        var response = mockMvc.perform(post("/consultas")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonConsulta.write(
                                            new DadosConsultaDTO(null, 1l, 1l, data, Especialidades.DERMATOLOGIA)).getJson()))
                               .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = jsonResposta.write(dadosRetorno).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Teste de cadastro de consulta retorna http 400 quando informações são enviadas.")
    @WithMockUser
    public void testeAtendamentoDeConsultaSemMedicoGeraErro() throws Exception {
        var response = mockMvc.perform(post("/consultas"))
        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
