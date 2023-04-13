import pandas as pd

# Chargement du fichier CSV dans un dataframe pandas
df = pd.read_csv('UpdatedResumeDataSet.csv')

# Création d'un dictionnaire vide pour stocker les données
data = {}

# Parcourir les lignes du dataframe
for i, row in df.iterrows():
    # Vérification si la catégorie existe déjà dans le dictionnaire
    if row['Category'] not in data:
        data[row['Category']] = []
    # Ajouter les données de chaque individu dans la liste correspondante à sa catégorie
    data[row['Category']].append(row['Resume'])

# Création d'un dataframe pandas à partir des données du dictionnaire
new_df = pd.DataFrame.from_dict(data, orient='index')

# Écriture du dataframe dans un fichier Excel ligne par ligne
with pd.ExcelWriter('nom_de_votre_fichier1.xlsx') as writer:
    for category, resume_list in data.items():
        # Création d'un dataframe pandas pour chaque catégorie et ses données
        category_df = pd.DataFrame({category: resume_list})
        # Écriture du dataframe dans le fichier Excel sans l'index
        category_df.to_excel(writer, sheet_name=category, index=False)
