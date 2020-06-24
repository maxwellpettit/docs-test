rm -rf out
mkdir out
# Collect
cat src/main/markdown/home.md > out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat src/main/markdown/Overview/Overview.md >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/UI & Screen Conventions/UI & Screen Conventions.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/User Management/User Management.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Daily Operations/Daily Operations.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Sales/Sales.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Item Inquiry/Item Inquiry.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Returns Management/Returns Management.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Tendering/Tendering.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Receipts/Receipts.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Customers/Customers.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Promotions/Promotions.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Tax/Tax.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Coupons/Coupons.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Peripherals/Peripherals.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Training Mode/Training Mode.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Orders/Orders.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Backoffice/Backoffice.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Beyond POS/Beyond POS.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md
cat "src/main/markdown/Appendix/Appendix.md" >> out/JumpMind-Commerce-User-Manual.md
echo $'\n' >>  out/JumpMind-Commerce-User-Manual.md

mkdir -p out/html/assets

ASSET_LOCATIONS=("src/main/markdown/Appendix/" "src/main/markdown/Backoffice/" "src/main/markdown/Tax/" "src/main/markdown/Orders/" "src/main/markdown/Training Mode/" "src/main/markdown/Peripherals/" "src/main/markdown/Coupons/" "src/main/markdown/Sales/" "src/main/markdown/UI & Screen Conventions/" "src/main/markdown/User Management/" "src/main/markdown/Daily Operations/" "src/main/markdown/Returns Management/" "src/main/markdown/Tendering/" "src/main/markdown/Receipts/" "src/main/markdown/Customers/" "src/main/markdown/Promotions/" "src/main/markdown/Beyond POS/")

RESOURCE_SEARCH_PATH=""

for i in "${ASSET_LOCATIONS[@]}"
do
   : 
   RESOURCE_SEARCH_PATH=$RESOURCE_SEARCH_PATH:"$i"
   echo cp -R "\"$i\"" out/html/
   cp -R "$i" out/html/
done

echo $RESOURCE_SEARCH_PATH

# Convert to docx
pandoc --resource-path "$RESOURCE_SEARCH_PATH" -s out/JumpMind-Commerce-User-Manual.md -o out/JumpMind-Commerce-User-Manual.docx

cp src/main/markdown/htmlStyle.css out/html
pandoc --css htmlStyle.css --resource-path "$RESOURCE_SEARCH_PATH" -s out/JumpMind-Commerce-User-Manual.md -o out/html/JumpMind-Commerce-User-Manual.html

rm -rf out/html/*.md

open out/JumpMind-Commerce-User-Manual.docx

open out/html/JumpMind-Commerce-User-Manual.html

echo "Generated out/JumpMind-Commerce-User-Manual.docx" 
