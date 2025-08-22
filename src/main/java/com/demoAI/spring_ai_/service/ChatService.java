package com.demoAI.spring_ai_.service;

import com.demoAI.spring_ai_.dto.BillItem;
import com.demoAI.spring_ai_.dto.ChatRequest;
import com.demoAI.spring_ai_.dto.ExpenseInfo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder, JdbcChatMemoryRepository jdbcChatMemoryRepository) {

        //Automation save request to database
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(30)
                .build();
        chatClient= builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();

    }

    public String chat(ChatRequest request){
        String covnversationID = "conversation3";
        SystemMessage systemMessage = new SystemMessage("""
                You are TroUni
                You should response with a formal voice""");

        UserMessage userMessage = new UserMessage(request.message());

        Prompt prompt = new Prompt(systemMessage,userMessage);
        return chatClient.
                prompt(prompt)
                .advisors(advisorSpec -> advisorSpec.param(
                        ChatMemory.CONVERSATION_ID, covnversationID
                ))
                .call()
                .content();
    }

    public List<BillItem> chatWithImage(MultipartFile file, String message) {
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(Objects.requireNonNull(file.getContentType())))
                .data(file.getResource())
                .build();

        //Set the creative of AI
        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0D)
                .build();

        return chatClient.prompt()
                .options(chatOptions)
                .system("You are TroUni")
                .user(promptUserSpec
                ->promptUserSpec.media(media)
                .text(message)
                ).call()
                .entity(new ParameterizedTypeReference<List<BillItem>>() {
                });
    }
}
