import csv

# Ouvrir le fichier CSV contenant les données des CV
with open('UpdatedResumeDataSet.csv', 'r', encoding='utf-8') as f:
    # Créer un objet reader pour lire les données
    reader = csv.reader(f)
    # Ignorer la première ligne (en-têtes des colonnes)
    next(reader)
    # Parcourir chaque ligne de données dans le fichier CSV
    for row in reader:
        # Vérifier que la ligne n'est pas vide et contient suffisamment de colonnes
        if row and len(row) >= 4:
            # Récupérer les données pour chaque colonne
            nom = row[0]
            Category = row[1]
            experience = int(row[2])
            diplome = row[3]
            # Utiliser les données pour recommander un cursus en fonction de la catégorie
            if Category == "Développeur":
                if experience >= 2:
                    print(f"{nom} devrait envisager un cursus en développement web.")
                else:
                    print(f"{nom} devrait envisager un cursus en développement logiciel.")
            elif Category == "Designer":
                if diplome == "Bac+2":
                    print(f"{nom} devrait envisager un cursus en design graphique.")
                else:
                    print(f"{nom} devrait envisager un cursus en design d'interface utilisateur.")
            elif Category == "Marketing":
                if experience >= 3:
                    print(f"{nom} devrait envisager un cursus en marketing digital.")
                else:
                    print(f"{nom} devrait envisager un cursus en marketing traditionnel.")
        else:
            print("Erreur : ligne de données invalide")
