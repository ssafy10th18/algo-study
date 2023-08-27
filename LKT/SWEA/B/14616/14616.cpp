#ifndef _CRT_SECURE_NO_WARNINGS
#define _CRT_SECURE_NO_WARNINGS
#endif

/////////////////////////////////////////////////////////////////////////
#include <stdio.h>

using namespace std;

#define MAX_SIZE 30000
#define MAX_TABLE 4096
#define HASH_KEY 5381

#define BUY 1
#define SELL 2
#define PRODUCT 3

struct Info {
    int id;
    int pId;
    int price;
    int quantity;
    Info* next;
    bool isCanceled;
} info_pool[2 * MAX_SIZE];

struct Product {
    int pId;
    int quantity;
    Product* next;
} product_pool[MAX_SIZE];

int info_cnt, product_cnt;

Info* bInfo[MAX_TABLE];
Info* sInfo[MAX_TABLE];
Product* pInfo[MAX_TABLE];

int getHash(int id) { return (((HASH_KEY << 5) + HASH_KEY) + id) % MAX_TABLE; }

void addInfo(Info* head, Info* toAdd) {
    Info* cur = head;
    if (cur->next == nullptr) {
        cur->next = toAdd;
    } else {
        toAdd->next = cur->next;
        cur->next = toAdd;
    }
}

void addProduct(Product* head, Product* toAdd) {
    Product* cur = head;
    if (cur->next == nullptr) {
        cur->next = toAdd;
    } else {
        toAdd->next = cur->next;
        cur->next = toAdd;
    }
}

Info* getNewInfo(int id, int pId, int price, int quantity) {
    info_pool[info_cnt].id = id;
    info_pool[info_cnt].pId = pId;
    info_pool[info_cnt].price = price;
    info_pool[info_cnt].quantity = quantity;
    info_pool[info_cnt].next = nullptr;
    info_pool[info_cnt].isCanceled = false;

    return &info_pool[info_cnt++];
}

Product* find(int pId) {}

Product* getNewProduct(int pId, int quantity) {
    product_pool[product_cnt].pId = pId;
    product_pool[product_cnt].quantity = quantity;
    product_pool[product_cnt].next = nullptr;

    return &product_pool[product_cnt++];
}

void init() {
    info_cnt = 0;
    product_cnt = 0;

    for (int i = 0; i < MAX_TABLE; i++) {
        bInfo[i]->next = nullptr;
        sInfo[i]->next = nullptr;
        pInfo[i]->next = nullptr;
    }
}

int buy(int bId, int mProduct, int mPrice, int mQuantity) {
    Info* info = getNewInfo(bId, mProduct, mPrice, mQuantity);
    addInfo(bInfo[getHash(bId)], info);

    Product* p = findProduct(pInfo[getHash(mProduct)]);
    if (p == nullptr) {
        Product* product = getNewProduct(mProduct, mQuantity);
        addProduct(pInfo[getHash(mProduct)], product);
    } else {
        p->quantity += mQuantity;
    }

    return p->quantity;
}

int cancel(int bId) { return 0; }

int sell(int sId, int mProduct, int mPrice, int mQuantity) { return 0; }

int refund(int sId) { return 0; }
/////////////////////////////////////////////////////////////////////////

#define CMD_INIT 1
#define CMD_BUY 2
#define CMD_CANCEL 3
#define CMD_SELL 4
#define CMD_REFUND 5

static bool run() {
    int q;
    scanf("%d", &q);

    int id, product, price, quantity;
    int cmd, ans, ret = 0;
    bool okay = false;

    for (int i = 0; i < q; ++i) {
        scanf("%d", &cmd);
        switch (cmd) {
            case CMD_INIT:
                init();
                okay = true;
                break;
            case CMD_BUY:
                scanf("%d %d %d %d %d", &id, &product, &price, &quantity, &ans);
                ret = buy(id, product, price, quantity);
                if (ans != ret) okay = false;
                break;
            case CMD_CANCEL:
                scanf("%d %d", &id, &ans);
                ret = cancel(id);
                if (ans != ret) okay = false;
                break;
            case CMD_SELL:
                scanf("%d %d %d %d %d", &id, &product, &price, &quantity, &ans);
                ret = sell(id, product, price, quantity);
                if (ans != ret) okay = false;
                break;
            case CMD_REFUND:
                scanf("%d %d", &id, &ans);
                ret = refund(id);
                if (ans != ret) okay = false;
                break;
            default:
                okay = false;
                break;
        }
    }
    return okay;
}

int main() {
    setbuf(stdout, NULL);
    freopen("input.txt", "r", stdin);

    int T, MARK;
    scanf("%d %d", &T, &MARK);

    for (int tc = 1; tc <= T; tc++) {
        int score = run() ? MARK : 0;
        printf("#%d %d\n", tc, score);
    }

    return 0;
}