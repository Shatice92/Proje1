package _08_KarisikSorular;

import java.util.Arrays;
import java.util.Scanner;

public class StudentManagement {
	static final int OGRENCI_KAPASITESI = 5;
	static final int OGRENCI_OZELLIK_SAYISI = 4;
	static Scanner scanner = new Scanner(System.in);
	static int ogrenciSayisi = 0;
	static String ogrencilerDizisi[][] = new String[OGRENCI_KAPASITESI][OGRENCI_OZELLIK_SAYISI];
	public static void main(String[] args) {
		secimMekanizmasi();
	}
	private static void secimMekanizmasi() {
		while (true) {
			int secim = getSecim();
			switch (secim) {
				case 1 -> ogrenciEkle();
				case 2 -> ogrenciListele();
				case 3 -> notGuncelle();
				case 4 -> ortHesaplama();
				case 5 -> ogrenciSil();
				case 6 -> kalanlarListele();
				case 0 -> {
					System.out.println("Çıkış yapılıyor...");
					return;
				}
				default -> System.out.println("Geçersiz seçim. Tekrar deneyin.");
			}
		}
	}
	
	private static int getSecim() {
		return notYonetimSisteminiGoster();
	}
	
	private static void kalanlarListele() {
		int notSiniri = 55;
		for (int i = 0; i < ogrenciSayisi; i++) {
			if (Integer.parseInt(ogrencilerDizisi[i][3]) < notSiniri) {
				System.out.println(ogrencilerDizisi[i][0] + " id li öğrenci sınıfta kaldı.");
			}
		}
	}
	
	private static void ogrenciSil() {
		System.out.println("Silinecek öğrenci ID'sini giriniz: ");
		int silinecekId = scanner.nextInt() - 1;
		if (silinecekId < 0 || silinecekId >= ogrenciSayisi) {
			System.out.println("Geçersiz öğrenci ID'si.");
			return;
		}
		for (int i = silinecekId; i < ogrenciSayisi - 1; i++) {
			String[] newOgrenciDizisi = new String[OGRENCI_KAPASITESI];
			newOgrenciDizisi[i] = Arrays.toString(ogrencilerDizisi[i+1 ]); 
		}
		ogrenciSayisi--; 
		System.out.println("Öğrenci silindi.");
	}
	
	private static double ortHesaplama() {
		double toplam = 0;
		for (int i = 0; i < ogrenciSayisi; i++) {
			toplam += Double.parseDouble(ogrencilerDizisi[i][3]);
		}
		
		double ort = toplam / ogrenciSayisi;
		System.out.println(ort);
		return ort;
	}
	
	private static void notGuncelle() {
		scanner.nextLine();
		String Id = kullanicidanGirdiAl("Lütfen notu güncellenecek öğrenciye ait id yi giriniz:");
		int index = indexBul(Id);
		if (index >= 0) {
			System.out.println("lütfen notu giriniz: ");
			ogrencilerDizisi[index][3] = scanner.next();
			System.out.println("Öğrenci notu Başarıyla Güncellendi.");
		}
		else {
			System.out.println("Id Bulunamadı");
		}
	}
	
	private static void ogrenciListele() {
		if (ogrenciSayisi == 0) {
			System.out.println("Listelenecek ogrenci yok..");
			return;
		}
		System.out.printf("%-5s %-20s %-20s %-10s%n", "ID", "Öğrenci Adı", "Ders", "Not");
		for (int i = 0; i < ogrenciSayisi; i++) {
			System.out.printf("%-5s %-20s %-20s %-10s%n", ogrencilerDizisi[i][0], ogrencilerDizisi[i][1],
			                  ogrencilerDizisi[i][2], ogrencilerDizisi[i][3]);
		}
		
	}
	
	private static int notYonetimSisteminiGoster() {
		System.out.println("""
				                   Öğrenci Not Yönetim Sistemi
				                   1. Öğrenci Ekle:
				                   2. Öğrenci Listele:
				                   3. Öğrenci Notu Güncelle:
				                   4. Öğrenci Not Ortalaması Hesaplama:
				                   5.Öğrenci Sil:
				                   6.Kalanlari Listele
				                   0. Çıkış
				                   Bir seçenek giriniz:""");
		int secim = scanner.nextInt();
		return secim;
	}
	
	private static void ogrenciEkle() {
		if (ogrenciSayisi >= OGRENCI_KAPASITESI) {
			System.out.println("Kapasite dolu, yeni öğrenci ekleyemezsiniz..");
		}
		ogrencilerDizisi[ogrenciSayisi][0] = String.valueOf(ogrenciSayisi + 1);
		System.out.println("Öğrenci ismini giriniz: ");
		ogrencilerDizisi[ogrenciSayisi][1] = scanner.next();
		System.out.println("Öğrenci dersini giriniz: ");
		ogrencilerDizisi[ogrenciSayisi][2] = scanner.next();
		System.out.println("Öğrenci notunu giriniz: ");
		ogrencilerDizisi[ogrenciSayisi][3] = scanner.next();
		ogrenciSayisi++;
		System.out.println("Ögrenci Eklendi..");
		
	}
	
	private static String kullanicidanGirdiAl(String mesaj) {
		System.out.println(mesaj);
		return scanner.nextLine();
	}
	
	private static int indexBul(String arananOgrenciId) {
		for (int i = 0; i < ogrenciSayisi; i++) {
			if (ogrencilerDizisi[i][0].equals(arananOgrenciId)) {
				return i;
			}
		}
		return -1;
	}
}
