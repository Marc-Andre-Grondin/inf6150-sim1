
/**
 * 
 * 
 * @author
 * @version 
 */
public class Sim_alpha5 {

    public static char lireOuiNon () {
        
        char reponse;
        
        System.out.print ( "Voulez-vous jouer une partie ? " );
        reponse = Clavier.lireChar ();
        Clavier.lireFinLigne ();  
        
        while ( reponse != 'o' && reponse != 'n' ) {
            System.out.print ( "*** vous devez repondre par o ou n : " );
            reponse = Clavier.lireChar ();
            Clavier.lireFinLigne ();
        }
        
        return reponse;
    } // lireOuiNon
    
    public static int lireSortePari () {
        
        int reponse;
        
        System.out.println ( "Quel pari voulez-vous faire ?" );
        System.out.print ( " 1 : paire, 2 : séquence, 3 : même couleur, 4 : somme <= 7 => " );
        reponse = Clavier.lireInt (); 
        
        while ( reponse < 1 || reponse > 4 ) {
            System.out.print ( "*** vous devez répondre par 1, 2, 3 ou 4 : " );
            reponse = Clavier.lireInt ();
        }
        
        return reponse;
    } // lireSortePari
    
    public static int lireMontantJoueur () {
    
        int reponse;
        
        System.out.print ( "Entrez le montant dont vous disposez : " );
        reponse = Clavier.lireInt();
        
        while ( reponse <= 0 ) {
            System.out.print ( "*** Le montant doit être supérieur à 0 : " );
            reponse = Clavier.lireInt();
        }
        
        return reponse;
    } // lireMontantJoueur

    public static int lireMiseJoueur ( int max ) {
    
        int reponse;
        
        System.out.print ( "Entrez le montant de la mise ( maximum : " + max + " ) : " );
        reponse = Clavier.lireInt();
        
        while ( reponse < 0 || reponse > max ) {
            System.out.print ( "*** Le montant doit être entre 0 et " + max + " : " );
            reponse = Clavier.lireInt();
        }
        
        return reponse;
    } // lireMiseJoueur
    
    public static int laSorte ( int carte ) {
        
    /* antécédent : 0 <= carte <= 51
     * conséquent : retourne la valeur de la carte (0, 1, ... 12)
     *              0 : as, 1 : 2, 2 : 3, ..., 9 : 10, 10 : valet, 11 : dame, 12 : roi
     */
    
        return carte % 13;
        
    } // laSorte
    
    public static int laCouleur ( int carte ) {
        
    /* antécédent : 0 <= carte <= 51
     * conséquent : retourne la couleur de la carte (0, 1, 2, 3)
     *              0 : coeur, 1 : carreau, 2 : trefle, 3 : pique
     */
    
        return carte / 13;
        
    } // laCouleur
    
    public static boolean estUnePaire ( int carte1, int carte2 ) { 

    /* antécédent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
     * conséquent : retourne vrai si carte1 et carte 2 constituent une paire,
     *              faux sinon
     */
    
      return laSorte ( carte1 ) == laSorte ( carte2 );
      
    } // estUnePaire

    public static boolean sontMemeCouleur ( int carte1, int carte2 ) { 

        /* antécédent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
         * conséquent : retourne vrai si carte1 et carte 2 sont de la même
         *              couleur.  Les 4 couleurs possibles sont : coeur, carreau,
         *              trèfle et pique.
         */
        
        return laCouleur ( carte1 ) == laCouleur ( carte2 );
        
    } // sontMemeCouleur

    public static boolean estUneSequence ( int carte1, int carte2 ) { 

        /* antécédent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
         * conséquent : retourne vrai si carte1 et carte 2 forment une s�quence,
         *              peu importe leur couleur, faux sinon.  Une s�quence de
         *              deux cartes sont deux cartes de valeur cons�cutive.  L'as
         *              et le 2 sont consid�r�es comme cons�cutives ainsi que l'as
         *              et le roi.
         */
    
        int sorte1 = laSorte ( carte1 );
        int sorte2 = laSorte ( carte2 );
    
        return sorte1 == sorte2 + 1 || 
               sorte1 == sorte2 - 1 ||
               sorte1 == 12 && sorte2 == 0 ||    // as et roi
               sorte2 == 12 && sorte1 == 0;
               
    } // estUneSequence
    
    public static boolean estInferieurOuEgalA7( int carte1, int carte2){
        /* antécédent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
         * conséquent : retourne vrai si la somme de carte1 + carte 2 est égale ou 
         *              inférieur à 7. Les as valent 1, les figures valent 10 et les autres 
         *              valent leur chiffre en valeur.
         */
        
        int sorte1 = laSorte(carte1);
        int sorte2 = laSorte(carte2);
        
        if (sorte1 == 0){
            sorte1 = 1;
        }
        
        if (sorte2 == 0){
            sorte2 = 1;
        }
        
        if (sorte1 > 9) {
            sorte1 = 10;
        }
        
        if (sorte2 > 9) {
            sorte2 = 10;
        }
        
        return (sorte1 + sorte2) <= 7;
    }

    public static String chaineCouleur ( int carte ) {
        
        String reponse;
        
        int couleur = laCouleur ( carte );
        if (couleur == 0) {
            reponse = "coeur";
        } else if (couleur == 1) {
            reponse = "carreau";
        } else if (couleur == 2) {
            reponse = "trefle";
        } else {
            reponse = "pique";
        }
        
        return reponse;
    } // chaineCouleur
    
    public static String chaineSorte ( int carte ) {
        
        String reponse;
        
        int sorte = laSorte ( carte );
        if (sorte == 0) {
            reponse = "as";
        } else if (sorte == 10) {
            reponse = "valet";
        } else if (sorte == 11) {
            reponse = "dame";
        } else if (sorte == 12) {
            reponse = "roi";
        } else {
            reponse = String.valueOf ( sorte + 1 );
        }
        
        return reponse;
    } // chaineCouleur
    
    public static void afficherCarte ( int carte ) { 

    /* antécédent : 0 <= carte <= 51
     * conséquent : Affiche la carte selon sa couleur et sa valeur
     */
    
        System.out.print ( chaineSorte ( carte ) + " " + chaineCouleur ( carte ) );
        
    } // afficherCarte
    
    public static void afficherLesDeuxCartes ( int carte1, int carte2 ) {
                          
        System.out.print ( "Voiçi la premiere carte : " );
        afficherCarte ( carte1 );
        System.out.println ();
            
        System.out.print ( "Voiçi la deuxieme carte : " );
        afficherCarte ( carte2 );
        System.out.println ( '\n' );
            
    } // afficherLesDeuxCartes

    public static void afficherFin ( int montant ) {
        
        System.out.println ( "Merci d'avoir joué avec moi !" );
        System.out.println ( "Vous quittez avec " + montant + " $ en poche." );
        
    } // afficherFin

    public static void initialiserJeuDeCarte () {
        
        System.out.print ( "Entrez un nombre entier pour initialiser le jeu : " );
        JeuDeCartes.initialiserJeuDeCarte ( Clavier.lireInt () );
        System.out.println ();
        
    } // initialiserJeuDeCarte

    public static void main ( String[] parametres ) {
                
        char    reponse;        // saisi : pour la reponse o ou n
        int     pari;           // saisi : pour la sorte de pari 1, 2 ou 3
        int     montantJoueur;  // saisi puis ajuste : montant dont dispose le joueur
        int     montantGagne;   // calcule : montant gagne selon le pari effectue
        
        int     mise;           // saisi : montant mise par le joueur
        int     deuxCartes;     // les deux cartes pigees par l'ordinateur
        int     carte1;         // la premiere carte pigee
        int     carte2;         // la deuxieme carte pigee
        int     coutPari = 3;   // un pari coûte 3 $
        
        boolean joueurGagne;    // si le joueur a gagne ou non la partie 
        
        // Initialiser le procede aleatoire
        
        initialiserJeuDeCarte ();
                
        // Saisir et valider le montant initial du joueur
        
        montantJoueur = lireMontantJoueur ();
        System.out.println ();
        
        // Boucle pour les parties
        
        reponse = lireOuiNon ();
        System.out.println ();
        
        while ( reponse == 'o' ) { 
            // saisie et validation du type de pari
            
            pari = lireSortePari ();
            System.out.println ();
            
            // saisie et validation du montant de la mise
            
            mise = lireMiseJoueur ( montantJoueur );
            System.out.println ();
            
            montantJoueur = montantJoueur - mise;
            
            // faire piger deux cartes par l'ordinateur
            
            deuxCartes = JeuDeCartes.pigerDeuxCartes ();
            
            carte1 = deuxCartes / 100;
            carte2 = deuxCartes % 100;
            
            afficherLesDeuxCartes ( carte1, carte2 );
            
            // determiner si le joueur a gagne ou perdu
            
            joueurGagne = false;
            
            if ( pari == 1 ) { // est-ce une paire ?
                joueurGagne = estUnePaire ( carte1, carte2 );
                montantGagne = 4 * mise;
            } else if ( pari == 2 ) { // est-ce une sequence ?
                joueurGagne = estUneSequence ( carte1, carte2 );
                montantGagne = 2 * mise;
            } else if ( pari == 3 ) { // deux de la meme couleur ?
                joueurGagne = sontMemeCouleur ( carte1, carte2 );
                montantGagne = mise;
            } else { // somme des deux inférieur ou égale à 7 ?
                joueurGagne = estInferieurOuEgalA7 ( carte1, carte2 );
                montantGagne = 5 * mise;
            }
            
            // afficher si le joueur a gagne ou perdu ainsi que son gain s'il y a lieu
            
            if ( joueurGagne ) {
                System.out.println ( "Bravo ! Vous avez gagné " + montantGagne + " $" );
                montantJoueur = montantJoueur + montantGagne;
            } else {
                System.out.println ( "Désolé ! Vous avez perdu votre mise !" );
            }
            
            // retrait du coût d'un pari
            
            montantJoueur = montantJoueur - coutPari;
            System.out.println("Un coût de " + coutPari + " $ a été retiré de votre montant d'argent.");
            
            System.out.println ();
            System.out.println ( "Vous disposez maintenant de " + montantJoueur + " $" );
            System.out.println ();
            
            // determiner si on continue ou pas
            
            if ( montantJoueur > 0 ) {
                reponse = lireOuiNon ();
            } else {
                System.out.println ( "Vous n'avez plus d'argent, vous ne pouvez continuer." );
                reponse = 'n';
            }

        } // boucle de jeu
        
        afficherFin ( montantJoueur );
        
    } // main
    
} // Tp2