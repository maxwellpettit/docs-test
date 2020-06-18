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

RESOURCE_SEARCH_PATH="src/main/markdown/User Management/:src/main/markdown/Sales/"

# Convert to docx
pandoc --resource-path "$RESOURCE_SEARCH_PATH" -s out/JumpMind-Commerce-User-Manual.md -o out/JumpMind-Commerce-User-Manual.docx

open out/JumpMind-Commerce-User-Manual.docx

echo "Generated out/JumpMind-Commerce-User-Manual.docx" 