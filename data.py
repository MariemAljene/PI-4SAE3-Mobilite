import pdfplumber
import nltk
nltk.download('stopwords')
nltk.download('punkt')
from rake_nltk import Rake

# Open the PDF file in binary mode
with pdfplumber.open('CV-MariemAljene.pdf') as pdf:
    # Initialize variables
    content = ""

    # Iterate through all pages of the PDF
    for page in pdf.pages:
        # Get the content of the page
        content += page.extract_text()

    # Initialize Rake object
    rake = Rake()

    # Extract keywords
    rake.extract_keywords_from_text(content)
    extracted_keywords = rake.get_ranked_phrases()

    # Print the extracted keywords
    print("Extracted keywords:")
    print(extracted_keywords)
