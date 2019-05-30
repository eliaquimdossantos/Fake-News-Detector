/**
 * Arquivo com a class News contendo a estrutura padr�o de uma not�cia
 */
package br.ufrn.imd.project.domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public abstract class News {
	String link; /* Link da not�cia */
	String date; /* Data da postagem */
	Article article; /* Artigo da not�cia */

	/**
	 * Nova not�cia
	 * 
	 * @param link Link da not�cia
	 * @param date Data da postagem
	 */
	public News(String link, String date) {
		this.link = link;
		this.date = date;
	}

	/**
	 * Trasformar o par�grafo em uma hash sha1
	 * 
	 * @param paragraph Texto do par�grafo j� tratado
	 * @return A hash sha1
	 */
	protected static String stringToHash(String paragraph) {
		try {
			// getInstance() method is called with algorithm SHA-1
			MessageDigest md = MessageDigest.getInstance("SHA-1");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(paragraph.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
