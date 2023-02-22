package com.example.datphongkhachsan;

public class room {
    String roomName;
    String kindRoom;
    String status;
    int price;

    public room(String roomName, String kindRoom, String status, int price) {
        this.roomName = roomName;
        this.kindRoom = kindRoom;
        this.status = status;
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getKindRoom() {
        return kindRoom;
    }

    public void setKindRoom(String kindRoom) {
        this.kindRoom = kindRoom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
