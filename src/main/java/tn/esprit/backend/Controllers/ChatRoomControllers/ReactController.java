package tn.esprit.backend.Controllers.ChatRoomControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.backend.Services.chatService.ReactService;
import tn.esprit.backend.model.chatModel.React;
import tn.esprit.backend.model.chatModel.TypeReact;

import java.util.List;

@RestController
@RequestMapping("chat/M")
public class ReactController {

    @Autowired
    ReactService reactService;
    @PostMapping("/AddReact/{messagId}")
    public React AddReact(@RequestBody React react, @PathVariable Long messagId){
        return reactService.AddReact(react,messagId);
    }
    @PutMapping("/updateReact/{messagId}")
    public React updateReact(@RequestBody React react,@PathVariable Long messagId){
        return reactService.updateReact(react,messagId);
    }
    @DeleteMapping("/DeleteReact/{messagId}/{idReact}")
    public void DeleteReact(@PathVariable Long idReact ,@PathVariable Long messagId){
        reactService.DeleteReact(idReact,messagId);
    }
    @GetMapping("/GetMessageReacts/{messageId}")
    public List<React> getMessageReacts(@PathVariable Long messageId){
        return reactService.getMessageReacts(messageId);
    }
    @GetMapping("/GetReactByType/{messageId}/{typeReact}")
    public List<React> getReactsByTypeReact(@PathVariable Long messageId, @PathVariable TypeReact typeReact){
        return reactService.getReactsByTypeReact(messageId,typeReact);
    }
}
