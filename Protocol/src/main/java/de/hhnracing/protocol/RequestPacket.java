package de.hhnracing.protocol;

public class RequestPacket extends Packet {

    RequestType type;

    RequestPacket(RequestType type) {
        this.type = type;
    }

}
