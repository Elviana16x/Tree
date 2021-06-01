package com.pinkly;
import java.util.Scanner;
class Node {
    int data;
    String nama;
    Node anak_kiri;
    Node anak_kanan;
    Node(int data, String nama) {
        this.data = data;
        this.nama = nama;
    }
    public String toString() {
        return nama + " memiliki data " + data;
    }
}
class BinaryTree {
    Node root;
    public void tambahNode(int data, String nama) {
        Node nodeBaru = new Node(data, nama);
        if (root == null) {
            root = nodeBaru;
        } else {
            Node titikNode = root;
            Node induk;
            while (true) {
                induk = titikNode;
                if (data < titikNode.data) {
                    titikNode = titikNode.anak_kiri;
                    if (titikNode == null) {
                        induk.anak_kiri = nodeBaru;
                        return;
                    }
                } else if (data > titikNode.data) {
                    titikNode = titikNode.anak_kanan;
                    if (titikNode == null) {
                        induk.anak_kanan = nodeBaru;
                        return;
                    }
                } else if (data == titikNode.data) {
                    titikNode = root;
                    titikNode = nodeBaru;
                    return;
                }
            }
        }
    }
    public void inOrderTraversalPohon(Node titikNode) {
        if (titikNode != null) {
            inOrderTraversalPohon(titikNode.anak_kiri);
            System.out.println(titikNode);
            inOrderTraversalPohon(titikNode.anak_kanan);
        }
    }
    public void preeOrderTraversalPohon(Node titikNode) {
        if (titikNode != null) {
            System.out.println(titikNode);
            preeOrderTraversalPohon(titikNode.anak_kiri);
            preeOrderTraversalPohon(titikNode.anak_kanan);
        }
    }
    public void postOrderTraversalPohon(Node titikNode) {
        if (titikNode != null) {
            postOrderTraversalPohon(titikNode.anak_kiri);
            postOrderTraversalPohon(titikNode.anak_kanan);
            System.out.println(titikNode);
        }
    }
    public boolean hapusNode(int data) {
        Node titikNode = root;
        Node induk = root;
        boolean ini_Aadalah_Aanak_Kiri = true;
        while (titikNode.data != data) {
            induk = titikNode;
            if (data < titikNode.data) {
                ini_Aadalah_Aanak_Kiri = true;
                titikNode = titikNode.anak_kiri;
            } else if (data > titikNode.data){
                ini_Aadalah_Aanak_Kiri = false;
                titikNode = titikNode.anak_kanan;
            } else if (data == titikNode.data) {
                titikNode = root;
            }
            if (titikNode == null) return false;
        }
        if (titikNode.anak_kiri == null && titikNode.anak_kanan == null) {
            if (titikNode == root) {
                root = null;
            } else if (ini_Aadalah_Aanak_Kiri) {
                induk.anak_kiri = null;
            } else {
                induk.anak_kiri = null;
            }
        } else if (titikNode.anak_kanan == null) {
            if (titikNode == root) {
                root = titikNode.anak_kiri;
            } else if (ini_Aadalah_Aanak_Kiri) {
                induk.anak_kiri = titikNode.anak_kiri;
            } else {
                induk.anak_kanan = titikNode.anak_kanan;
            }
        } else if (titikNode.anak_kiri == null) {
            if (titikNode == root) {
                root = titikNode.anak_kanan;
            } else {
                induk.anak_kanan = titikNode.anak_kanan;
            }
        } else {
            Node replacement = getReplacemnetNode(titikNode);
            if (titikNode == root) {
                root = replacement;
            } else if (ini_Aadalah_Aanak_Kiri) {
                induk.anak_kiri = replacement;
            } else {
                induk.anak_kanan = replacement;
                replacement.anak_kiri = titikNode.anak_kiri;
            }
            return true;
        }
        return ini_Aadalah_Aanak_Kiri;
    }
    public Node getReplacemnetNode(Node replaceNode) {
        Node replacementParent = replaceNode;
        Node replacement = replaceNode;
        Node titikNode = replaceNode.anak_kanan;
        while (titikNode != null) {
            replacementParent = replacement;
            replacement = titikNode;
            titikNode = titikNode.anak_kiri;
        }
        if (replacement != replaceNode.anak_kanan) {
            replacementParent.anak_kiri = replacement.anak_kanan;
            replacement.anak_kanan = replaceNode.anak_kiri;
        }
        return replacement;
    }
    public void cariNode(int data) {
        Node titikNode = root;
        do {
            if (data < titikNode.data && titikNode.anak_kiri != null) {
                titikNode = titikNode.anak_kiri;
                System.out.println(data + " ini adalah anak kiri");
            } else if (data > titikNode.data && titikNode.anak_kanan != null) {
                titikNode = titikNode.anak_kanan ;
                System.out.println(data + " adalah anak kanan");
            } else if (data == titikNode.data) {
                titikNode = root;
                System.out.println(data + " adalah root");
            } else {
                System.out.println(data + " tidak ditemukan");
            }
            if (titikNode == null) titikNode = null;
            break;
        } while (titikNode.data == data);
    }
}
public class PohonBiner {
    static Scanner input = new Scanner(System.in);
    static void tampilkanData() {
        BinaryTree Pohon = new BinaryTree();
        Pohon.tambahNode(50, "boss");
        Pohon.tambahNode(25, "wakil boss");
        Pohon.tambahNode(15, "manajer kantor");
        Pohon.tambahNode(30, "seketaris");
        Pohon.tambahNode(75, "manajer penjualan");
        Pohon.tambahNode(85, "penjual");
        System.out.println("\n");
        System.out.println("inOrder" + "\n");
        Pohon.inOrderTraversalPohon(Pohon.root);
        System.out.print("\n");
        System.out.println("preeOrder" + "\n");
        Pohon.preeOrderTraversalPohon(Pohon.root);
        System.out.print("\n");
        System.out.println("hapus node" + "\n");
        System.out.println("meghapus Node 15 ");
        Pohon.hapusNode(15);
        System.out.print("\n");
        System.out.println("postOrder" + "\n");
        Pohon.postOrderTraversalPohon(Pohon.root);
        System.out.print("\n");
        System.out.println("mencari Node" + "\n");
        Pohon.cariNode(50);
        Pohon.cariNode(25);
        Pohon.cariNode(15);
        Pohon.cariNode(30);
        Pohon.cariNode(75);
        Pohon.cariNode(85);
        System.out.print("\n");
    }
    public static void main(String[] args) {
        System.out.print("\n");
        System.out.println("Belajar Membuat Program Binary Tree" + "\n");
        tampilkanData();
        System.out.println("Program Selesai");
        System.out.print("\n");
    }
}
        /*
        System.out.println("menghitung jumlah node" + "\n");
        System.out.println("hitung titik di bawah untuk mengetahui jumlah node" + "\n");
        Pohon.jumlahNode(Pohon.root);
        System.out.println("\n");
        System.out.println("tinggi pohon" + "\n");
        Pohon.tinggiPohon(Pohon.root);
        System.out.println("\n");
        System.out.print("inOrder" + "\n");
        Pohon.inOrderTraversalPohon(Pohon.root);
        System.out.print("\n");
        System.out.println("preeOrder" + "\n");
        Pohon.preeOrderTraversalPohon(Pohon.root);
        System.out.print("\n");
        System.out.println("hapus node" + "\n");
        System.out.println("meghapus Node 15 ");
        Pohon.hapusNode(15, "removed");
        System.out.print("\n");
        System.out.println("postOrder" + "\n");
        Pohon.postOrderTraversalPohon(Pohon.root);
        System.out.print("\n");
        System.out.print("mencari Node" + "\n");
        Pohon.cariNode(50);
        Pohon.cariNode(25);
        Pohon.cariNode(15);
        Pohon.cariNode(30);
        Pohon.cariNode(75);
        Pohon.cariNode(85);
        System.out.print("\n");
        */
