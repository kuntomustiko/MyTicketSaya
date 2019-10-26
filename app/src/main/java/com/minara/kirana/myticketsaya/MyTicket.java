package com.minara.kirana.myticketsaya;

public class MyTicket {

    String namaWisata, lokasi;
    String jumlahTicket;

    public MyTicket() {
    }

    public MyTicket(String namaWisata, String lokasi, String jumlahTicket) {
        this.namaWisata = namaWisata;
        this.lokasi = lokasi;
        this.jumlahTicket = jumlahTicket;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJumlahTicket() {
        return jumlahTicket;
    }

    public void setJumlahTicket(String jumlahTicket) {
        this.jumlahTicket = jumlahTicket;
    }
}
