package com.stgutah.playground.generics;

/**
 * Sub-lists and wild-cards.
 * <p/>
 * User: dqromney
 * Date: Feb 3, 2011
 * Time: 12:07:16 AM
 */
public class SubListWildCards {

    private CagedAnimal<Animal> animalCage = null;
    private CagedBigCat<BigCat> bigCatCage = null;
    private CagedFlyingInsect<FlyingInsect> flyingInsectCage = null;

    // WildCard '?'
    private Cage<Animal> aCage = null;
    private Cage<BigCat> bcCage = null;
    private Cage<FlyingInsect> fiCage = null;
    // upper bound; an unknown type that is a subtype of Animal, possibly Animal itself
    private Cage<? extends Animal> subtypeCage = null;
    // lower-bound; an unknown type that is a supertype of Animal, possibly Animal itself
    private Cage<? super Animal> supertypeCage = null;

    private void execute() {
        System.out.println(String.format("animalCage.size()=[%1$d]", animalCage.size()));
        System.out.println(String.format("lionCage.size()=[%1$d]", bigCatCage.size()));
        System.out.println(String.format("butterflyCage.size()=[%1$d]", flyingInsectCage.size()));

        animalCage.add(new Tiger("Juniper"));
        animalCage.add(new Tiger("Daisy"));
        animalCage.add(new Butterfly("Monarch"));
        animalCage.add(new Butterfly("Tigerlilly"));

        System.out.println("\nAnimal Cage list...");
        for(Animal animal : animalCage) {
            System.out.println(animal.getName());
        }

        bigCatCage.add(new Tiger("Juniper"));
        bigCatCage.add(new Tiger("Daisey"));
        bcCage.add(new Tiger("Big Tooth"));
        aCage.add(new Tiger("Suzzie"));
        // bigCatCage.add(new Butterfly("Monarch")); // Syntax error
        System.out.println("Big Cat Cage list...");
        for(Animal bigCat : bigCatCage) {
            System.out.println(bigCat.getName());
        }

        flyingInsectCage.add(new Butterfly("Monarch"));
        flyingInsectCage.add(new Butterfly("Tigerlilly"));
        fiCage.add(new Butterfly("Zebra"));
        aCage.add(new Butterfly("Yellow Tail"));
        // flyingInsectCage.add(new Tiger("Daisey")); // Syntax error
        System.out.println("Flying Insects Cage list...");
        for(Animal insect : flyingInsectCage) {
            System.out.println(insect.getName());
        }

        // Can't do this, but...
        // flyingInsectCage = bigCatCage; // Incompatible types
        // animalCage = flyingInsectCage; // Incompatible types
        // animalCage = bigCatCage; // Incompatible types

        // ... I can do this!
        subtypeCage = bcCage;
        System.out.println("\nAnother (subtype) Big Cat Cage list...");
        for(Animal item : subtypeCage) {
            System.out.println(item.getName());
        }
        subtypeCage = fiCage;
        System.out.println("Another (subtype) Flying Insect Cage list...");
        for(Animal item : subtypeCage) {
            System.out.println(item.getName());
        }
        subtypeCage = aCage;
        System.out.println("Another (subtype) Animal Cage list...");
        for(Animal item : subtypeCage) {
            System.out.println(item.getName());
        }

        supertypeCage = aCage; // this is okay because it's an Animal (just don't know what kind), but ...
        System.out.println("\nAnother (supertype) Animal Cage list...");
        for(Object item : supertypeCage.toArray()) {
            if(item instanceof Butterfly) {
                System.out.println("FlyingInsect " + ((FlyingInsect)item).getName());
            } else if (item instanceof Tiger){
                System.out.println("BigCat " + ((BigCat)item).getName());
            }
        }
        //supertypeCage = fiCage; // incompatible type exception; look for Animal or above
        //supertypeCage = bcCage; // incompatible type exception; look for Animal or above
    }

    private void init() {
        subtypeCage = new Cage<Animal>();
        supertypeCage = new Cage<Animal>();
        animalCage = new CagedAnimal<Animal>();
        bigCatCage = new CagedBigCat<BigCat>();
        flyingInsectCage = new CagedFlyingInsect<FlyingInsect>();

        aCage = new Cage<Animal>();
        bcCage = new Cage<BigCat>();
        fiCage = new Cage<FlyingInsect>();
    }

    public static void main(String[] args) throws Exception {
        SubListWildCards main = new SubListWildCards();
        main.init();
        main.execute();
    }

}

/**
 * Tiger class.
 */
class Tiger implements BigCat {

    private String name;

    Tiger(String name) {
        this.name = name;
    }

    /**
     * Get the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     *
     * @param pName name of big cat
     */
    public void setName(String pName) {
        this.name = pName;
    }
}

/**
 * Butterfly class.
 */
class Butterfly implements FlyingInsect {

    private String name;

    Butterfly(String name) {
        this.name = name;
    }

    /**
     * Get the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     *
     * @param pName name of flying insect
     */
    public void setName(String pName) {
        this.name = pName;
    }
}

