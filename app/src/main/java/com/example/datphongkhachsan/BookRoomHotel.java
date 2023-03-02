package com.example.datphongkhachsan;

public class BookRoomHotel {
    String idRoom;
    String idUser;
    String Time;
    String idBookRoomHotel;

    public BookRoomHotel(String idRoom, String idUser, String time , String idBookRoomHotel) {
        this.idRoom = idRoom;
        this.idUser = idUser;
        this.Time = time;
        this.idBookRoomHotel = idBookRoomHotel;
    }

    public String getIdBookRoomHotel() {
        return idBookRoomHotel;
    }

    public void setIdBookRoomHotel(String idBookRoomHotel) {
        this.idBookRoomHotel = idBookRoomHotel;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


}
