controller
외부 요청과 응답에 대한 영역, API계층
클라이언트 요청 처리 로직, 필요 로직 호출

- client: request for monthly collection info
    func: public ResponseEntity getCollection(@RequestParam("user_id") int user_id,
                                          @RequestParam("year") int year,
                                          @RequestParam("month") int month)


- client: request for post(add) collection
    func: public ResponseEntity postCollection(@RequestParam("user_id") int user_id,
                                                   @RequestParam("year") int year,
                                                   @RequestParam("month") int month,
                                                   @RequestParam("word")int word_id)
