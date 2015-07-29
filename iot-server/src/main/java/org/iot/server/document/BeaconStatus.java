package org.iot.server.document;

import org.springframework.data.annotation.Id;

public class BeaconStatus {

    @Id
    private String id;

    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    //TODO prepare data
}
