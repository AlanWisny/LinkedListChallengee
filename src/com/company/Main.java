package com.company;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {
        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.06);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.30);
        album.addSong("Hold on", 5.06);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.20);
        album.addSong("Soldier of fortune", 3.13);
        albums.add(album);

        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Let's go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<Song>();
        albums.get(0).addToPlayList("You can't do it right", playList);
        albums.get(0).addToPlayList("Holy man", playList);
        albums.get(0).addToPlayList("Holy diver", playList); // does not exist

        albums.get(1).addToPlayList(9, playList);
        albums.get(1).addToPlayList(8, playList);
        albums.get(1).addToPlayList(3, playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(1).addToPlayList(24, playList); // doest not exist

        play(playList);
    }

    private static void play(LinkedList<Song> playList) {
        Scanner s = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> it = playList.listIterator();

        if(playList.size() == 0) {
            System.out.println("No songs in the playlist");
            return;
        } else {
            System.out.println("Now playing: " + it.next().toString());
            printMenu();
        }

        while(!quit) {
            int action = s.nextInt();
            s.nextLine();

            switch(action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;

                case 1:
                    if(!forward) {
                        if(it.hasNext()) {
                            it.next();
                        }
                        forward = true;
                    }
                    if(it.hasNext()) {
                        System.out.println("Now playing: " + it.next().toString());
                    } else {
                        System.out.println("We have reached the end of the playlist.");
                        forward = false;
                    }
                    break;

                case 2:
                    if(forward) {
                        if(it.hasPrevious()) {
                            it.previous();
                        }
                        forward = false;
                    }
                    if(it.hasPrevious()) {
                        System.out.println("Now playing: " + it.previous().toString());
                    } else {
                        System.out.println("We are at the start of the playlist.");
                        forward = true;
                    }
                    break;

                case 3:
                    if(forward) {
                        if(it.hasPrevious()) {
                            System.out.println("Now replaying " + it.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if(it.hasNext()) {
                            System.out.println("Now replaying " + it.next().toString());
                            forward = true;
                        } else {
                            System.out.println("Reached the end of the list");
                        }
                    }
                    break;

                case 4:
                    printList(playList);
                    break;

                case 5:
                    printMenu();
                    break;
                case 6:
                    if(playList.size() > 0) {
                        it.remove();
                        if(it.hasNext()) {
                            System.out.println("Now playing: " + it.next().toString());
                        } else if(it.hasPrevious()) {
                            System.out.println("Now playing: " + it.next().toString());
                        }
                    }
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                            "1 - to play the next song\n" +
                            "2 - to play previous song\n" +
                            "3 - to replay the current song\n" +
                            "4 - list songs in the playlist\n" +
                            "5 - print available actions\n" +
                            "6 - delete current song from the playlist");
    }

    private static void printList(LinkedList<Song> list) {
        Iterator<Song> it = list.iterator();
        System.out.println("==================================");

        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
        System.out.println("===================================");
    }

}
