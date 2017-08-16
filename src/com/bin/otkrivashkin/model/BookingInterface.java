package com.bin.otkrivashkin.model;

public interface BookingInterface {


    void bookClient(Client client);

    void bookClient(Client client, RoomType type);

    void bookClient(Client client, double price);

    void unBookClient(Client client);
}
