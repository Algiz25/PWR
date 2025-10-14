#include "tasks.h"
#include "CTable.h"

int main() {
    cout << "Task 1" << endl;
    v_alloc_table_fill_34(4);

    cout << "Task 2" << endl;
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

    cout << "Task 3" << endl;
    b_dealloc_table_2_dim(&piTable);

    cout << "Task 4" << endl;

    CTable cTable = CTable("firstOne", 5);
    cTable.i_table[0] = 0;
    cTable.i_table[1] = 1;
    cTable.i_table[2] = 2;
    cTable.i_table[3] = 3;
    cTable.i_table[4] = 4;

    print_tab(cTable);

    v_mod_tab(&cTable, 3);
    print_tab(cTable);

    v_mod_tab(cTable, 5);
    print_tab(cTable);

    CTable *pcNewTable = new CTable();
    pcNewTable->vSetName("MyTable");
    for (int i = 0; i < pcNewTable->i_table_len; i++) {
        pcNewTable->i_table[i] = i*2;
    }
    print_tab(*pcNewTable);

    delete pcNewTable;

    return 0;
}