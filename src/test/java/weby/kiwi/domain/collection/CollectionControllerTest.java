package weby.kiwi.domain.collection;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import weby.kiwi.domain.collection.controller.CollectionController;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.service.CollectionService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class CollectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CollectionService collectionService;


    @Test
    public void testGetCollectionByUserIdAndMonth() throws Exception {

        Long userId = 1L;
        Integer month = 8;
        List<CollectionResDto> collectionDtoList = collectionService.findCollectionByUserIdAndMonth(userId, month);

        // GET 요청을 보내고 응답 확인
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/collection/collections")
                .param("userId", userId.toString())
                .param("month", month.toString())
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].collectionId").value(collectionDtoList.get(0).getCollectionId()))
                .andExpect(jsonPath("$[0].userId").value(collectionDtoList.get(0).getUserId()))
                .andExpect(jsonPath("$[0].wordName").value(collectionDtoList.get(0).getWordName()))
                .andExpect(jsonPath("$[0].month").value(collectionDtoList.get(0).getMonth()))
                .andDo(print());
    }
}