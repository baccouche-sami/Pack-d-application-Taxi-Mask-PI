package fr.et.intechinfo.mousqinfos.taximask.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommandeResponse {
    private String tokenUn;
    private Long id;

    public CommandeResponse(String tokenUn, Long id) {
        this.tokenUn = tokenUn;
        this.id = id;
    }
}
