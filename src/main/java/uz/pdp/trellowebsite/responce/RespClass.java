package uz.pdp.trellowebsite.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.trellowebsite.entity.Comment;
import uz.pdp.trellowebsite.entity.Task;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespClass {
    private Task task;
    private Comment comment;
}
