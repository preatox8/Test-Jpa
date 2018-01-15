import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibliotheque.Client;
import bibliotheque.Emprunt;
import bibliotheque.Livre;

/**
 * @author ETY2
 *
 */
public class TestJpa {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestJpa.class);

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_essai");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		/**
		 * R�cup�ration du livre avec l'id 2 et m�thode find()
		 */
		Livre l = em.find(Livre.class, 2);
		if (l != null){
			System.out.println("");
			LOGGER.info("1) ===================================> Livre : " + "ID : " + l.getId() + " -> Titre : "  + l.getTitre() + " -> Auteur : "+ l.getAuteur());
			System.out.println("");
		}
		
		
		/**
		 * 
		 * R�cup�ration d'un livre en fonction de son titre avec une requete JPQL, ici le livre avec id = 3 avec 2 m�thode
		 * 
		 */
		
		/**
		 * 1�re M�thode avec typage dans requete
		 */
		TypedQuery<Livre> query2 = em.createQuery("select l from Livre l where l.titre='Apprendre � parler aux animaux'", Livre.class);
		Livre l2 = query2.getResultList().get(0);
		System.out.println("");
		LOGGER.info("2) ===================================> Livre : " + "ID : " +  l2.getId() + " -> Titre : "  + l2.getTitre() + " -> Auteur : " +l2.getAuteur());
		System.out.println("");

		/**
		 * 2nde M�thode sans typage dans requete et avec param�tre
		 */
		Query query = em.createQuery("select l from Livre l where l.titre=:titre");
		query.setParameter("titre", "Apprendre � parler aux animaux");
		Livre l1 = (Livre) query.getResultList().get(0);
		
		System.out.println("");
		LOGGER.info("3) ===================================> Livre : " + "ID : " +  l2.getId() + " -> Titre : "  + l2.getTitre() + " -> Auteur : " + l2.getAuteur());
		System.out.println("");
		
		
		/**
		 * Requete pour extraire un emprunt avec tout ses livres
		 */
		TypedQuery<Emprunt> query3 = em.createQuery("from Emprunt e WHERE e.id='1' ", Emprunt.class);
		Emprunt e = query3.getResultList().get(0);
		List<Livre> livre1 = e.getLivre();
		
		if(e != null) {
			System.out.println("");
			LOGGER.info("4) ===================================>  Emprunt : " + e.getId());
			for(Livre li : livre1) {
				LOGGER.info("Livre : " + li.getTitre() + " -> " + li.getAuteur());
			}
			System.out.println("");
		}
		
		/**
		 * Requete pour extraire un emprunt avec tout ses livres
		 */
		TypedQuery<Client> query4 = em.createQuery("from Client c WHERE c.id=2 ", Client.class);
		Client cli = query4.getResultList().get(0);
		List<Emprunt> emprunt1 = cli.getEmprunt();
		
		if(cli != null) {
			System.out.println("");
			LOGGER.info("5) ===================================>  Client : " + cli.getId());
			for(Emprunt emp : emprunt1) {
				LOGGER.info("Emprunt : " + emp.getId() + " -> " + emp.getDate_debut() + " / " + emp.getDate_fin());
			}
			System.out.println("");
		}
		
		
		
		em.close();
		entityManagerFactory.close();
		
		EntityManagerFactory entityManagerFactory1 = Persistence.createEntityManagerFactory("jpa_banque");
		EntityManager em1 = entityManagerFactory1.createEntityManager();
		
		em1.close();
		entityManagerFactory1.close();
	}

}
