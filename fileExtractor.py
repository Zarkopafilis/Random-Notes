import os
from fnmatch import fnmatch
import shutil

def unique(seq):
    seen = set()
    return [seen.add(x) or x for x in seq if x not in seen]

def safeDictGet(dictionary , key):
    if(key in dictionary):
        return dictionary[key]
    else:
        return 0

print "---File Extractor---"
print ""

root = unicode(raw_input("Directory to extract files from: "))

print "Analyzing filetypes..."

extensions = []
nums = {}

i=0

for path, subdirs, files in os.walk(root):
    for name in files:
        ext = "No extension"
        if "." in name:
            ext = unicode(name).split(".")[-1]
        extensions.append(ext)
        nums[ext] = safeDictGet(nums , ext) + 1
        i+=1

extensions = unique(extensions)

print "File types available: "

for ext in extensions:
    print ext + " - " + str(nums[ext]) + " files"

print "Total files: " + str(i)
print "No extension = files with no actual extension"

target = unicode(raw_input("Directory to save extracted files to: "))

if not os.path.exists(target):
    print "Target directory does not exist, creating it for you..."
    os.makedirs(target)

pattern = unicode(raw_input("File extensions (Split by '-'): "))
pattern = pattern.split("-")

grabFilesWithoutExtension = False
gfwo = raw_input("Grab files without extension?(yes/no)")
if(gfwo == "yes"):
    gfwo = True

mkdirs = raw_input("Make folders based on file type?(yes/no)")
if(mkdirs == "yes"):
    print "Generating directories"
    for ext in pattern:
        if not os.path.exists(ext):
            print "Creating directory for " + ext + " files"
            os.makedirs(os.path.join(target , ext))

i=0

for path, subdirs, files in os.walk(root):
    for name in files:
        for ext in pattern:
            if fnmatch(unicode(name) , unicode("*." + ext)) or (gfwo and "." not in name):
                x = unicode(os.path.join(unicode(path), unicode(name)))
                print "Copying file: " + x
                if "." not in name:
                    y = os.path.join(os.path.join(target , "no_extension") , name)
                    if not os.path.exists(y):
                        os.makedirs(y)
                    shutil.copy2(unicode(x) , y)
                else:    
                    shutil.copy2(unicode(x) , os.path.join(os.path.join(target , ext) , name))
                i+=1

print "Total files moved: " + str(i)

raw_input("Press any key to exit...")
