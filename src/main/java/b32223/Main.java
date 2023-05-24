package b32223;

import b32223.EntityManager.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Start a transaction
        if(em.isOpen()){
            System.out.println("Open");
            Product product1 = new Product();
            product1.setNom("OS");
            product1.setLabel("OS");
            product1.setType(ProdType.FOOD);
            product1.setPrice(8.0);

            Product product2 = new Product();
            product2.setNom("Laisse");
            product2.setLabel("Laisse");
            product2.setType(ProdType.ACCESSORY);
            product2.setPrice(10.0);

            Product product3 = new Product();
            product3.setNom("Cage");
            product3.setLabel("Cage");
            product3.setType(ProdType.CLEANING);
            product3.setPrice(50.0);

            em.persist(product1);
            em.persist(product2);
            em.persist(product3);

            PetStore petStore1 = new PetStore();
            petStore1.setNom("ChienEtEtChat");
            petStore1.setManagerName("Francis");

            PetStore petStore2 = new PetStore();
            petStore2.setNom("Animaux");
            petStore2.setManagerName("Tony");

            PetStore petStore3 = new PetStore();
            petStore3.setNom("AventureDesAnimaux");
            petStore3.setManagerName("Ribéri");

            em.persist(petStore1);
            em.persist(petStore2);
            em.persist(petStore3);

            Address address1 = new Address();
            address1.setNumber("12");
            address1.setStreet("Rue des anges");
            address1.setCity("Orion");
            address1.setZipCode("14500");
            address1.setPetStore(petStore1);

            Address address2 = new Address();
            address2.setNumber("15");
            address2.setStreet("Rue perdue");
            address2.setCity("France");
            address2.setZipCode("90000");
            address2.setPetStore(petStore2);

            Address address3 = new Address();
            address3.setNumber("17");
            address3.setStreet("Rue de la vie");
            address3.setCity("Alcool");
            address3.setZipCode("696969");
            address3.setPetStore(petStore3);

            em.persist(address1);
            em.persist(address2);
            em.persist(address3);

            Fish poisson1 = new Fish();
            poisson1.setLivingEnv(FishLivEnv.FRESH_WATER);
            poisson1.setBirth(LocalDate.now());
            poisson1.setCouleur("Beige");
            poisson1.setPetStore(petStore1);

            Fish poisson2 = new Fish();
            poisson2.setLivingEnv(FishLivEnv.SEA_WATER);
            poisson2.setBirth(LocalDate.now());
            poisson2.setCouleur("Noir");
            poisson2.setPetStore(petStore2);

            Fish poisson3 = new Fish();
            poisson3.setLivingEnv(FishLivEnv.SEA_WATER);
            poisson3.setBirth(LocalDate.now());
            poisson3.setCouleur("Blanc");
            poisson3.setPetStore(petStore3);

            em.persist(poisson1);
            em.persist(poisson2);
            em.persist(poisson3);

            Cat chat1 = new Cat();
            chat1.setChipId("7777");
            chat1.setBirth(LocalDate.now());
            chat1.setCouleur("Rouge");
            chat1.setPetStore(petStore1);

            Cat chat2 = new Cat();
            chat2.setChipId("7979");
            chat2.setBirth(LocalDate.now());
            chat2.setCouleur("Bleu");
            chat2.setPetStore(petStore2);

            Cat chat3 = new Cat();
            chat3.setChipId("7878");
            chat3.setBirth(LocalDate.now());
            chat3.setCouleur("Vert");
            chat3.setPetStore(petStore3);

            em.persist(chat1);
            em.persist(chat2);
            em.persist(chat3);

            petStore1.getProducts().add(product1);
            petStore1.getProducts().add(product2);

            petStore2.getProducts().add(product3);
            petStore2.getProducts().add(product1);

            petStore3.getProducts().add(product2);
            petStore3.getProducts().add(product3);

            String query = "SELECT a FROM Animal a JOIN a.petStore p WHERE p.id = :IdPetStore";
            List<Animal> animals = em.createQuery(query, Animal.class)
                    .setParameter("IdPetStore", 1L)
                    .getResultList();

            for (Animal animal : animals) {
                if (animal instanceof Cat) {
                    Cat cat = (Cat) animal;
                    System.out.println("Chat - Couleur : " + cat.getCouleur() + ", Date de naissance : " + cat.getBirth());
                } else if (animal instanceof Fish) {
                    Fish fish = (Fish) animal;
                    System.out.println("Poisson - Environnement de vie : " + fish.getLivingEnv() + ", Date de naissance : " + fish.getBirth());
                }
            }

            em.getTransaction().commit();
        } else {
            System.out.println("non open");
        }
        em.close(); // Close the EntityManager
        emf.close();
    }
}