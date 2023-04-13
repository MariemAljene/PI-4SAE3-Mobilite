import pdfplumber
import re

# Open the PDF file in binary mode
with pdfplumber.open('CV-MariemAljene.pdf') as pdf:

    # Initialize variables
    first_name = ""
    last_name = ""
    email = ""
    phone = ""
    education = ""
    experience = ""
    leadership_activities = ""
    skills_interests = ""
    personal = ""

    # Iterate through all pages of the PDF
    for page in pdf.pages:

        # Get the content of the page
        content = page.extract_text()

        # Define the regular expressions to extract information
        name_regex = r"(?i)(\b[a-z]+\b)\s*(\b[a-z]+\b)"
        email_regex = r"(?i)([a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,})"
        phone_regex = r"(?i)(\+?\d{10,13})"
        education_regex = r"(?i)education\s*((?:.|\n)*?)\s*(?=experience|leadership and activities|skills & interests|personal|$)"
        experience_regex = r"(?i)experience\s*((?:.|\n)*?)\s*(?=education|leadership and activities|skills & interests|personal|$)"
        leadership_activities_regex = r"(?i)leadership and activities\s*((?:.|\n)*?)\s*(?=education|experience|skills & interests|personal|$)"
        skills_interests_regex = r"(?i)skills & interests\s*((?:.|\n)*?)\s*(?=education|experience|leadership and activities|personal|$)"
        personal_regex = r"(?i)personal\s*((?:.|\n)*?)\s*(?=education|experience|leadership and activities|skills & interests|$)"

        # Search for the name
        name_match = re.search(name_regex, content)
        if name_match:
            first_name = name_match.group(1)
            last_name = name_match.group(2)

        # Search for the email
        email_match = re.search(email_regex, content)
        if email_match:
            email = email_match.group(1)

        # Search for the phone number
        phone_match = re.search(phone_regex, content)
        if phone_match:
            phone = phone_match.group(1)

        # Search for the education
        education_match = re.search(education_regex, content)
        if education_match:
            education = education_match.group(1).strip()

        # Search for the experience
        experience_match = re.search(experience_regex, content)
        if experience_match:
            experience = experience_match.group(1).strip()

        # Search for the leadership and activities
        leadership_activities_match = re.search(leadership_activities_regex, content)
        if leadership_activities_match:
            leadership_activities = leadership_activities_match.group(1).strip()

        # Search for the skills & interests
        skills_interests_match = re.search(skills_interests_regex, content)
        if skills_interests_match:
            skills_interests = skills_interests_match.group(1).strip()

        # Search for the personal information
        personal_match = re.search(personal_regex, content)
        if personal_match:
            personal = personal_match.group(1).strip()

    # Print the results
   # Print the results with underlined fields
print("First Name:   ", first_name)
print("-" * 50) # underline the fields

print("Last Name:    ", last_name)
print("-" * 50) # underline the fields

print("Email:        ", email)
print("-" * 50) # underline the fields

print("Phone:        ", phone)
print("-" * 50) # underline the fields

print("Education:    ", education)
print("-" * 50) # underline the fields

print("Experience:   ", experience)
print("-" * 50) # underline the fields

print("Leadership & Activities: ", leadership_activities)
print("-" * 50) # underline the fields

print("Skills & Interests:      ", skills_interests)
print("-" * 50) # underline the fields

print("Personal Info: ", personal)
print("-" * 50) # underline the fields



    

