package com.demoAI.spring_ai_.Controller;

import com.demoAI.spring_ai_.dto.BillItem;
import com.demoAI.spring_ai_.dto.ChatRequest;
import com.demoAI.spring_ai_.dto.ExpenseInfo;
import com.demoAI.spring_ai_.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ChatController {

  private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    String chat(@RequestBody ChatRequest request){
        return chatService.chat(request);
    }

    @PostMapping(value = "/chat-with-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<BillItem> chatWithImage(@RequestParam("file")MultipartFile file,
                                @RequestParam("message")String message){
        return chatService.chatWithImage(file,message);
    }
}
