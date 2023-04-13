import pandas as pd

# Charger le dataset dans un DataFrame
df = pd.read_csv('UpdatedResumeDataSet.csv')

# Compter le nombre d'occurrences de chaque catégorie
count = df['Category'].value_counts()

# Calculer le pourcentage de chaque catégorie
percent = count / len(df) * 100

# Créer un DataFrame à partir des résultats
result_df = pd.DataFrame({'Category': count.index, 'Count': count.values, 'Percent': percent.values})

# Écrire le DataFrame dans un fichier Excel
result_df.to_excel('resultats.xlsx', index=False)
