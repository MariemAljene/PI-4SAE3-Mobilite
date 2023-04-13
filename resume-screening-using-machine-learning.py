#!/usr/bin/env python
# coding: utf-8

# ![1](data:image/jpeg;base64,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)

# Companies often receive thousands of resumes for each job posting and employ dedicated screening officers to screen qualified candidates.
# 
# Hiring the right talent is a challenge for all businesses. This challenge is magnified by the high volume of applicants if the business is labour-intensive, growing, and facing high attrition rates.
# 
# 
# IT departments are short of growing markets. In a typical service organization, professionals with a variety of technical skills and business domain expertise are hired and assigned to projects to resolve customer issues. This task of selecting the best talent among many others is known as Resume Screening.
# 
# Typically, large companies do not have enough time to open each CV, so they use machine learning algorithms for the Resume Screening task.

# In[5]:


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import warnings
warnings.filterwarnings('ignore')
from sklearn.naive_bayes import MultinomialNB
from sklearn.multiclass import OneVsRestClassifier
from sklearn import metrics
from sklearn.metrics import accuracy_score
from pandas.plotting import scatter_matrix
from sklearn.neighbors import KNeighborsClassifier
from sklearn import metrics

resumeDataSet = pd.read_csv('C:/Users/Rihab/Downloads/archive/UpdatedResumeDataSet.csv', encoding='utf-8')
resumeDataSet['cleaned_resume'] = ''
resumeDataSet.head()


# In[38]:


#This will display the first five rows of the dataframe by default


# In[35]:


resumeDataSet.head()


# In[ ]:


#This will output a summary of the dataframe, including the number of rows and columns, the data types of each column,
#and the number of non-null values in each column.
#It can be useful for quickly getting an overview of the dataset.


# In[6]:


resumeDataSet.info()


# In[ ]:


#It will display the unique categories present in the 'Category' column of the dataframe.


# In[7]:


print ("Displaying the distinct categories of resume:\n\n ")
print (resumeDataSet['Category'].unique())


# In[ ]:


#This code displays the distinct categories of resumes that are present in the dataset along with the number of 
#records belonging to each category. 
#The output shows the count of resumes for each category.


# In[8]:


print ("Displaying the distinct categories of resume and the number of records belonging to each category:\n\n")
print (resumeDataSet['Category'].value_counts())


# In[ ]:


#This code generates a bar plot using the Seaborn library to visualize the distribution of resume categories in the dataset.
-


# In[9]:


import seaborn as sns
plt.figure(figsize=(20,5))
plt.xticks(rotation=90)
ax=sns.countplot(x="Category", data=resumeDataSet)
for p in ax.patches:
    ax.annotate(str(p.get_height()), (p.get_x() * 1.01 , p.get_height() * 1.01))
plt.grid()


# In[ ]:


#This code creates a pie chart to display the distribution of categories in the resume dataset.
#It uses the GridSpec function from matplotlib.gridspec to create a 2x2 grid of subplots, and then creates a pie chart in one 
#of the subplots using the pie function from matplotlib.pyplot.
#The labels and counts for each category are passed to the pie function, along with formatting options for the percentage
#values displayed on the chart.
#Finally, the chart is displayed using the show function from matplotlib.pyplot.


# In[10]:


from matplotlib.gridspec import GridSpec
targetCounts = resumeDataSet['Category'].value_counts()
targetLabels  = resumeDataSet['Category'].unique()
# Make square figures and axes
plt.figure(1, figsize=(22,22))
the_grid = GridSpec(2, 2)


cmap = plt.get_cmap('coolwarm')
plt.subplot(the_grid[0, 1], aspect=1, title='CATEGORY DISTRIBUTION')

source_pie = plt.pie(targetCounts, labels=targetLabels, autopct='%1.1f%%', shadow=True)
plt.show()


# In[ ]:


#This code defines a function cleanResume() that takes in a text string (presumably a resume) and performs various 
#text cleaning operations on it using regular expressions.
#operation(removing URLs, RT and cc (which are often used on Twitter), hashtags, mentions, punctuations, and non-ASCII characters)
#Then the function removes any extra whitespace in the text and returns the cleaned text.
#===> This allows for the original resumes to be preserved while also having a cleaned version for further analysis.


# In[39]:


import re
def cleanResume(resumeText):
    resumeText = re.sub('http\S+\s*', ' ', resumeText)  # remove URLs
    resumeText = re.sub('RT|cc', ' ', resumeText)  # remove RT and cc
    resumeText = re.sub('#\S+', '', resumeText)  # remove hashtags
    resumeText = re.sub('@\S+', '  ', resumeText)  # remove mentions
    resumeText = re.sub('[%s]' % re.escape("""!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~"""), ' ', resumeText)  # remove punctuations
    resumeText = re.sub(r'[^\x00-\x7f]',r' ', resumeText) 
    resumeText = re.sub('\s+', ' ', resumeText)  # remove extra whitespace
    return resumeText
    
resumeDataSet['cleaned_resume'] = resumeDataSet.Resume.apply(lambda x: cleanResume(x))


# In[ ]:


# creates a copy of the resumeDataSet dataframe and assigns it to a new variable called resumeDataSet_d


# In[40]:


resumeDataSet_d=resumeDataSet.copy()


# In[ ]:


# LabelEncoder: convert the categorical variable "Category" from strings to integers
#The LabelEncoder maps each unique category to a different integer, which can then be used in the machine learning model. 
#The original resumeDataSet is modified in place.


# In[41]:


from sklearn.preprocessing import LabelEncoder

var_mod = ['Category']
le = LabelEncoder()
for i in var_mod:
    resumeDataSet[i] = le.fit_transform(resumeDataSet[i])


# In[ ]:


#This will return the count of each unique value in the 'Category' column of the resumeDataSet dataframe,
#after encoding it using LabelEncoder().
#understanding decode LabelEncoder


# In[24]:


resumeDataSet.Category.value_counts()


# In[26]:


del resumeDataSet_d #clearing the space occupied 


# In[ ]:


#1-It defines a function called "cleanResume" to clean up the text in the "Resume" column of the dataset. 
#This involves removing URLs, RT and cc mentions, hashtags, mentions, punctuation, and non-ASCII characters.
#2-It creates a copy of the original dataset.
#3-It encodes the "Category" column of the dataset using LabelEncoder from scikit-learn.
#4-It splits the dataset into training and testing sets with a 80/20 ratio.
#5-It applies TfidfVectorizer from scikit-learn to convert the text in the "cleaned_resume" column into a matrix of 
#word features.
#6-It prints the shapes of the training and testing feature matrices.


# In[27]:


from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from scipy.sparse import hstack

requiredText = resumeDataSet['cleaned_resume'].values
requiredTarget = resumeDataSet['Category'].values

word_vectorizer = TfidfVectorizer(
    sublinear_tf=True,
    stop_words='english')
word_vectorizer.fit(requiredText)
WordFeatures = word_vectorizer.transform(requiredText)

print ("Feature completed .....")

X_train,X_test,y_train,y_test = train_test_split(WordFeatures,requiredTarget,random_state=42, test_size=0.2,
                                                 shuffle=True, stratify=requiredTarget)
print(X_train.shape)
print(X_test.shape)


# In[ ]:


#This code trains a KNeighbors Classifier model using the training data (X_train and y_train), 
#then makes predictions on the test data (X_test) and prints the accuracy score for both the training and test sets.
#The OneVsRestClassifier is used to handle multiclass classification, and KNeighborsClassifier is the algorithm used
#for classification


# In[28]:


clf = OneVsRestClassifier(KNeighborsClassifier())
clf.fit(X_train, y_train)
prediction = clf.predict(X_test)
print('Accuracy of KNeighbors Classifier on training set: {:.2f}'.format(clf.score(X_train, y_train)))
print('Accuracy of KNeighbors Classifier on test set:     {:.2f}'.format(clf.score(X_test, y_test)))



# In[ ]:


#This code prints the classification report for the classifier.
#The classification_report() function is imported from the sklearn.metrics module.
#It takes two arguments - the true labels of the test set (y_test) and the predicted labels (prediction) - 
#and computes various evaluation metrics such as precision, recall, and F1-score for each class. 
#The report includes these metrics as well as the overall accuracy of the model.


# In[30]:


print("\n Classification report for classifier %s:\n%s\n" % (clf, metrics.classification_report(y_test, prediction)))


# In[1]:


get_ipython().system('pip install surprise')


# In[2]:


from surprise import Dataset, Reader
from surprise import SVD
from surprise.model_selection import train_test_split
from surprise import accuracy

# Charger les données
reader = Reader(rating_scale=(1, 5))
data = Dataset.load_from_df(df[['user_id', 'item_id', 'rating']], reader)

# Fractionner les données en ensembles d'entraînement et de test
trainset, testset = train_test_split(data, test_size=0.25)

# Initialiser l'algorithme de factorisation de matrice
algo = SVD()

# Entraîner le modèle sur l'ensemble d'entraînement
algo.fit(trainset)

# Faire des prédictions sur l'ensemble de test
predictions = algo.test(testset)

# Calculer l'exactitude du modèle
accuracy.rmse(predictions)


# In[ ]:


for prediction in predictions:
    print(prediction)


# In[ ]:




