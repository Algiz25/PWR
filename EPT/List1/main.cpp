#include "tasks.h"
#include "CTable.h"

int main() {
    cout << "   Task 1" << endl;
    v_alloc_table_fill_34(4);

    cout << "   Task 2" << endl;
    int **piTable;
    int iSizeX = 3;
    int iSizeY = 5;
    b_alloc_table_2_dim(&piTable, iSizeX, iSizeY);

    for (int i = 0; i < iSizeX; i++) {
        for (int j = 0; j < iSizeY; j++) {
            cout << piTable[i][j] << " ";
        }
        cout << endl;
    }

    cout << "   Task 3" << endl;
    b_dealloc_table_2_dim(&piTable);
    cout << "memory freed" << endl;

    cout << "   Task 4" << endl;

    int iTableLen = 5;
    CTable cTable = CTable("firstOne", iTableLen);

    for (int i = 0; i < iTableLen; i++) {
        cTable.setTabVal(i, i);
    }
    print_tab(cTable);

    v_mod_tab(&cTable, 3); //edits original table
    print_tab(cTable);

    v_mod_tab(cTable, 5); //makes a copy
    print_tab(cTable);

    CTable *pcNewTable = new CTable();
    pcNewTable->vSetName("MyTable");
    for (int i = 0; i < pcNewTable->getTabLen(); i++) {
        pcNewTable->setTabVal(i, i * 2);
    }
    print_tab(*pcNewTable);

    delete pcNewTable;

    return 0;
}
