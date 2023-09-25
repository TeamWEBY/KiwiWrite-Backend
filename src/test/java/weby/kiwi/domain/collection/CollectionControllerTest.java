package weby.kiwi.domain.collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.collection.controller.CollectionController;
import weby.kiwi.domain.collection.repository.CollectionRepository;

@SpringBootTest
@Transactional
@Rollback
public class CollectionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CollectionController collectionController;

    @Test
    public void getCollectionByUserIdAndMonthTest() throws Exception {
        // 테스트에 사용할 파라미터 값
        Long userId = 1L;
        Integer month = 8;

        // 엔드포인트 호출 및 결과 검증
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/collection/collections")
                        .param("userId", userId.toString())
                        .param("month", month.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andReturn();
    }
}
