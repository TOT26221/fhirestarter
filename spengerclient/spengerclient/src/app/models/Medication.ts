import { CodeableConcept, Identifier, Reference } from "./Encounter";

export class Medication {
    constructor(
        public id: string = "",
        public text: string = "",
        public code?: CodeableConcept,
        public identifier?: Array<Identifier>,
        public status?: string,
        public manufacturer?: Reference,
        public form?: CodeableConcept,
        public amount?: Ratio,
        public batch?: Batch,
        public ingredient?: Array<Ingredient>,
    ) {}
}

export class Ratio {
    constructor(
        public numerator?: Quantity,
        public denominator?: Quantity,
    ) {}
}

export class Quantity {
    constructor(
        public value?: number,
        public comparator?: string,
        public unit?: string,
        public system?: string,
        public code?: string,
    ) {}
}

export class Batch {
    constructor(
        public lotNumber?: string,
        public expirationDate?: Date,
    ) {}
}

export class Ingredient {
    constructor(
        public itemCodeableConcept?: CodeableConcept,
        public itemReference?: Reference,
        public isActive?: boolean,
        public strength?: Ratio,
    ) {}
}