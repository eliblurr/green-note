package org.tlc.domain.base.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketPubSubDTO {

    private String content;

    @Override
    public String toString() {
        return "WebSocketContent{content='" + this.content + "'" + "}";
    }
}
