
/**
 * 
 * 
 * @author
 * @version 
 */
public class Sim_alpha5 {

    public static char lireOuiNon () {

        String reponse;
        char retour;

        System.out.print ( "Voulez-vous jouer une partie ? " );

        // amelioration apportee : lecture dune chaine pour ne pas permettre les cas ambigues
        reponse = Clavier.lireString ();

        while ( !reponse.equals("o") && !reponse.equals("n") ) {
            System.out.print ( "*** vous devez repondre par o ou n : " );
            reponse = Clavier.lireString ();
        }

        retour = reponse.charAt(0);

        return retour;
    } // lireOuiNon

    public static int lireSortePari () {

        int reponse;

        System.out.println ( "Quel pari voulez-vous faire ?" );
        System.out.print ( " 1 : paire, 2 : sequence, 3 : meme couleur, 4 : somme <= 7 => " );
        reponse = Clavier.lireInt (); 

        while ( reponse < 1 || reponse > 4 ) {
            System.out.print ( "*** vous devez repondre par 1, 2, 3 ou 4 : " );
            reponse = Clavier.lireInt ();
        }

        return reponse;
    } // lireSortePari

    public static int lireMontantJoueur () {

        int reponse;

        System.out.print ( "Entrez le montant dont vous disposez : " );
        reponse = Clavier.lireInt();

        while ( reponse <= 0 ) {
            System.out.print ( "*** Le montant doit être superieur à 0 : " );
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

        /* antecedent : 0 <= carte <= 51
         * consequent : retourne la valeur de la carte (0, 1, ... 12)
         *              0 : as, 1 : 2, 2 : 3, ..., 9 : 10, 10 : valet, 11 : dame, 12 : roi
         */

        return carte % 13;

    } // laSorte

    public static int laCouleur ( int carte ) {

        /* antecedent : 0 <= carte <= 51
         * consequent : retourne la couleur de la carte (0, 1, 2, 3)
         *              0 : coeur, 1 : carreau, 2 : trefle, 3 : pique
         */

        return carte / 13;

    } // laCouleur

    public static boolean estUnePaire ( int carte1, int carte2 ) { 

        /* antecedent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
         * consequent : retourne vrai si carte1 et carte 2 constituent une paire,
         *              faux sinon
         */

        return laSorte ( carte1 ) == laSorte ( carte2 );

    } // estUnePaire

    public static boolean sontMemeCouleur ( int carte1, int carte2 ) { 

        /* antecedent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
         * consequent : retourne vrai si carte1 et carte 2 sont de la même
         *              couleur.  Les 4 couleurs possibles sont : coeur, carreau,
         *              trèfle et pique.
         */

        return laCouleur ( carte1 ) == laCouleur ( carte2 );

    } // sontMemeCouleur

    public static boolean estUneSequence ( int carte1, int carte2 ) { 

        /* antecedent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
         * consequent : retourne vrai si carte1 et carte 2 forment une sequence,
         *              peu importe leur couleur, faux sinon.  Une sequence de
         *              deux cartes sont deux cartes de valeur consecutive.  L'as
         *              et le 2 sont considerees comme consecutives ainsi que l'as
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
        /* antecedent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
         * consequent : retourne vrai si la somme de carte1 + carte 2 est égale ou 
         *              inférieur a 7. Les as valent 1, les figures valent 10 et les autres 
         *              valent leur chiffre en valeur.
         */

        int sorte1 = laSorte(carte1) + 1; // pour avoir la bonne valeur
        int sorte2 = laSorte(carte2) + 1;

        if (sorte1 > 10) {
            sorte1 = 10;
        }

        if (sorte2 > 10) {
            sorte2 = 10;
        }

        return (sorte1 + sorte2) <= 7;
    }

    public static String chaineCouleur ( int carte ) {

        String reponse;

        int couleur = laCouleur ( carte );

        // amelioration apportee : if -> switch
        switch ( couleur ) {
            case 0  :   reponse = "coeur";
                        break;
            case 1  :   reponse = "carreau";
                        break;
            case 2  :   reponse = "trefle";
                        break;
            default :   reponse = "pique";
                        break;
        }

        return reponse;
    } // chaineCouleur

    public static String chaineSorte ( int carte ) {

        String reponse;

        int sorte = laSorte ( carte );

        // amelioration apportee : if -> switch
        switch ( sorte ) {
            case 0  :   reponse = "as";
                        break;
            case 10 :   reponse = "valet";
                        break;
            case 11 :   reponse = "dame";
                        break;
            case 12 :   reponse = "roi";
                        break;
            default :   reponse = String.valueOf ( sorte + 1 );
                        break;
        }

        return reponse;
    } // chaineCouleur

    public static void afficherCarte ( int carte ) { 

        /* antecedent : 0 <= carte <= 51
         * consequent : Affiche la carte selon sa couleur et sa valeur
         */

        // amelioration apportee : affichage simple des cartes
        CarteGraphique carteG = new CarteGraphique();

        // amelioration apportee : ajo
        System.out.println ( chaineSorte ( carte ) + " " + chaineCouleur ( carte ) );
        carteG.imprimerCarte(carte);
    } // afficherCarte

    public static void afficherLesDeuxCartes ( int carte1, int carte2 ) {

        System.out.print ( "Voici la premiere carte : " );
        afficherCarte ( carte1 );

        System.out.print ( "Voici la deuxieme carte : " );
        afficherCarte ( carte2 );
        System.out.println ( "\n" );

    } // afficherLesDeuxCartes

    public static void afficherSommeDesCartes ( int carte1, int carte2) {
        ++carte1; // les cartes vont de 0 à 12. Ceci est pour avoir la bonne valeur
        ++carte2;

        if(carte1 > 10){
            carte1 = 10;
        }

        if(carte2 > 10){
            carte2 = 10;
        }

        System.out.println( "La somme des cartes est : " + carte1 + " + " + carte2 + " = " + (carte1 + carte2) );
    }

    public static void afficherFin ( int montant ) {

        System.out.println ( "Merci d'avoir joue avec moi !" );
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
        int     montantGagne = 0;   // calcule : montant gagne selon le pari effectue

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

            // retrait du cout dun pari avant le montant de la mise pour etre sur que le joueur a ce montant en main

            montantJoueur = montantJoueur - coutPari;
            System.out.println("Un cout de " + coutPari + " $ a ete retire de votre montant d'argent.");

            // saisie et validation du montant de la mise

            mise = lireMiseJoueur ( montantJoueur );
            System.out.println ();

            montantJoueur = montantJoueur - mise;

            // faire piger deux cartes par l'ordinateur

            deuxCartes = JeuDeCartes.pigerDeuxCartes ();

            carte1 = deuxCartes / 100;
            carte2 = deuxCartes % 100;

            afficherLesDeuxCartes ( carte1, carte2 );
            afficherSommeDesCartes ( laSorte(carte1), laSorte(carte2) );

            // determiner si le joueur a gagne ou perdu

            joueurGagne = false;

            //amelioration apportee : serie de if en switch

            switch ( pari ) {
                case 1 :    joueurGagne = estUnePaire ( carte1, carte2 );
                montantGagne = 4 * mise;
                break;
                case 2 :    joueurGagne = estUneSequence ( carte1, carte2 );
                montantGagne = 2 * mise;
                break;
                case 3 :    joueurGagne = sontMemeCouleur ( carte1, carte2 );
                montantGagne = mise;
                break;
                case 4 :    joueurGagne = estInferieurOuEgalA7 ( carte1, carte2 );
                montantGagne = 5 * mise;
                break;
            }

            // afficher si le joueur a gagne ou perdu ainsi que son gain s'il y a lieu

            if ( joueurGagne ) {
                System.out.println ( "Bravo ! Vous avez gagne " + montantGagne + " $" );
                montantJoueur = montantJoueur + montantGagne;
            } else {
                System.out.println ( "Desole ! Vous avez perdu votre mise !" );
            }

            System.out.println ();
            System.out.println ( "Vous disposez maintenant de " + montantJoueur + " $" );
            System.out.println ();

            // determiner si on continue ou pas

            if ( montantJoueur > (coutPari + 1) ) {
                reponse = lireOuiNon ();
            } else {
                System.out.println ( "Vous n'avez plus assez d'argent, vous ne pouvez continuer." );
                reponse = 'n';
            }

        } // boucle de jeu

        afficherFin ( montantJoueur );

    } // main

} // Sim_alpha5