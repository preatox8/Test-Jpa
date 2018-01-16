import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Banque.Adresse;
import Banque.Assurancevie;
import Banque.Banque;
import Banque.Client;
import Banque.Compte;
import Banque.Livreta;
import Banque.Operation;
import Banque.Virement;
import bibliotheque.Emprunt;
import bibliotheque.Livre;
import bibliotheque.Clientbibliotheque;;

/**
 * @author ETY2
 *
 */
public class TestJpa {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestJpa.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * CREATION D'UN ENTITY MANAGER QUI PREND POUR DEPENDENCE UNITAIRE LE BLOC
		 * NOMMEE : JPA_BANQUE
		 */
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_essai");
		EntityManager em = entityManagerFactory.createEntityManager();

		/**
		 * Récupération du livre avec l'id 2 et méthode find()
		 */
		Livre l = em.find(Livre.class, 2);
		if (l != null) {
			System.out.println("");
			LOGGER.info("1) ===================================> Livre : " + "ID : " + l.getId() + " -> Titre : "
					+ l.getTitre() + " -> Auteur : " + l.getAuteur());
			System.out.println("");
		}

		/**
		 * Récupération d'un livre en fonction de son titre avec une requete JPQL, ici
		 * le livre avec id = 3 avec 2 méthode
		 */

		/**
		 * 1ère Méthode avec typage dans requete
		 */
		TypedQuery<Livre> query2 = em
				.createQuery("select l from Livre l where l.titre='Apprendre à parler aux animaux'", Livre.class);
		Livre l2 = query2.getResultList().get(0);
		System.out.println("");
		LOGGER.info("2) ===================================> Livre : " + "ID : " + l2.getId() + " -> Titre : "
				+ l2.getTitre() + " -> Auteur : " + l2.getAuteur());
		System.out.println("");

		/**
		 * 2nde Méthode sans typage dans requete et avec paramètre
		 */
		Query query = em.createQuery("select l from Livre l where l.titre=:titre");
		query.setParameter("titre", "Apprendre à parler aux animaux");
		Livre l1 = (Livre) query.getResultList().get(0);
		System.out.println("");
		LOGGER.info("3) ===================================> Livre : " + "ID : " + l2.getId() + " -> Titre : "
				+ l2.getTitre() + " -> Auteur : " + l2.getAuteur());
		System.out.println("");

		/**
		 * Requete pour extraire un emprunt avec tout ses livres
		 */
		TypedQuery<Emprunt> query3 = em.createQuery("from Emprunt e WHERE e.id='1' ", Emprunt.class);
		Emprunt e = query3.getResultList().get(0);
		List<Livre> livre1 = e.getLivre();
		if (e != null) {
			System.out.println("");
			LOGGER.info("4) ===================================>  Emprunt : " + e.getId());
			for (Livre li : livre1) {
				LOGGER.info("Livre : " + li.getTitre() + " -> " + li.getAuteur());
			}
			System.out.println("");
		}

		/**
		 * Requete pour extraire un emprunt avec tout ses livres
		 */
		/*
		 * TypedQuery<Clientbibliotheque> query4 =
		 * em.createQuery("from Client c WHERE c.id=2 ", Clientbibliotheque.class);
		 * Clientbibliotheque cli = query4.getResultList().get(0); List<Emprunt>
		 * emprunt1 = cli.getEmprunt();
		 * 
		 * if(cli != null) { System.out.println("");
		 * LOGGER.info("5) ===================================>  Client : " +
		 * cli.getId()); for(Emprunt emp : emprunt1) { LOGGER.info("Emprunt : " +
		 * emp.getId() + " -> " + emp.getDate_debut() + " / " + emp.getDate_fin()); }
		 * System.out.println(""); }
		 */

		/**
		 * FERMETURE DE L'ENTITY MANAGER
		 */
		em.close();
		entityManagerFactory.close();

		/************************************************************************************************************************************
		 * * PARTIE BANQUE SANS HERITAGE * *
		 ************************************************************************************************************************************/

		/**
		 * CREATION D'UN ENTITY MANAGER QUI PREND POUR DEPENDENCE UNITAIRE LE BLOC
		 * NOMMEE : JPA_BANQUE
		 */
		EntityManagerFactory entityManagerFactory1 = Persistence.createEntityManagerFactory("jpa_banque");
		EntityManager em1 = entityManagerFactory1.createEntityManager();

		/**
		 * OUVERTURE D'UNE TRANSACTION
		 */
		EntityTransaction transaction = em1.getTransaction();
		transaction.begin();

		try {

			Banque banque = new Banque();// création d'une instance de banque
			banque.setNom("Credit agricole");
			em1.persist(banque);

			Client client = new Client();// création d'une instance de client
			client.setNom("FERRE");
			client.setPrenom("Jordan");
			client.setDatenaissance(LocalDate.of(1995, 03, 05));
			Adresse adresse = new Adresse();
			adresse.setCodepostal(85470);
			adresse.setNumero(25);
			adresse.setRue("Chemin de la chabossiere");
			adresse.setVille("BREM SUR MER");
			client.setAdresse(adresse);
			em1.persist(client);

			Operation operation = new Operation();// création d'une instance de operation
			operation.setDate(LocalDateTime.of(2018, 01, 16, 11, 03));
			operation.setMontant(1000D);
			operation.setMotif("ajout operation du 16-01-2018");
			em1.persist(operation);

			Compte compte = new Compte();// création d'une instance de compte
			compte.setNumero("151631165");
			compte.setSolde(666D);
			em1.persist(compte);

			Compte compte1 = new Compte();// création d'une instance de compte
			compte1.setNumero("151631165");
			compte1.setSolde(666D);
			em1.persist(compte1);

			transaction.commit();

		} catch (HibernateException e1) {
			LOGGER.info("Erreur ajout BDD : ", e1.getMessage());
			if (transaction.isActive()) {
				transaction.rollback();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}

		/************************************************************************************************************************************
		 * * PARTIE BANQUE AVEC HERITAGE * *
		 ************************************************************************************************************************************/

		/**
		 * OUVERTURE D'UNE TRANSACTION
		 */
		EntityTransaction transaction1 = em1.getTransaction();
		transaction1.begin();

		try {

			Banque banque1 = new Banque();// création d'une instance de banque
			banque1.setNom("rgergre");
			em1.persist(banque1);

			Adresse adresse1 = new Adresse();// création d'une instance de adresse
			adresse1.setNumero(45);
			adresse1.setRue("gfregerghre");
			adresse1.setCodepostal(12584);
			adresse1.setVille("rgergr");

			Operation operation1 = new Operation();// création d'une instance de operation
			operation1.setDate(LocalDateTime.of(2005, 03, 15, 12, 32));
			operation1.setMontant(5214D);
			operation1.setMotif("rgeethtrtr");
			em1.persist(operation1);

			Client client1 = new Client();// création d'une instance de client
			client1.setNom("rgrgre");
			client1.setPrenom("thtrhtrh");
			client1.setDatenaissance(LocalDate.of(1995, 03, 05));
			client1.setAdresse(adresse1);
			client1.setBanque(banque1);
			em1.persist(client1);

			Compte compte2 = new Compte();// création d'une instance de compte
			compte2.setNumero("124654");
			compte2.setSolde(4146D);
			compte2.getOperation().add(operation1);
			compte2.getClientcompte().add(client1);
			em1.persist(compte2);

			Livreta liva = new Livreta(); // création d’une instance de livret A
			liva.setTaux(1.5D);
			liva.setNumero("52");
			liva.setSolde(4566D);
			em1.persist(liva);

			Assurancevie assvie = new Assurancevie(); // création d’une instance de assurance vie
			assvie.setTaux(1.2D);
			assvie.setDatefin(LocalDate.of(2019, 11, 8));
			assvie.setNumero("456789852");
			assvie.setSolde(25000D);
			em1.persist(assvie);

			Virement vir = new Virement(); // création d'une instance de virement
			vir.setMontant(5000D);
			vir.setDate(LocalDateTime.of(2018, 01, 16, 20, 52));
			vir.setMotif("test ajout avec table fille virement");
			vir.setCompte(compte2);
			vir.setBeneficiaire("Anthony");
			em1.persist(vir);

			transaction1.commit();

		} catch (HibernateException e2) {
			LOGGER.info("erreur ajout :", e2.getMessage());
			if (transaction1.isActive()) {
				transaction1.rollback();
			}
		} finally {
			if (em1 != null && em1.isOpen()) {
				em1.close();
			}
		}

		/**
		 * FERMETURE DE L'ENTITY MANAGER
		 */
		em1.close();
		entityManagerFactory1.close();
	}

}
