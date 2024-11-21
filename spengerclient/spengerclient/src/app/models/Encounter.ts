export class Encounter {
    constructor(
        public id: string = "",
        public resourceType: string = "Encounter",
        public status?: Array<StatusCode>,
        public identifier?: Array<Identifier>,
        public name: string = "",
        public telecom?: Array<ContactPoint>,
        public active: boolean = false,
        public gender: string = "unknown",
        public birthDate: Date = new Date(1000, 1, 1),
        public deceasedBoolean?: boolean,
        public deceasedDateTime?: Date,
        public multipleBirthBoolean?: boolean,
        public multipleBirthInteger?: number
    ) {}}

    export class HumanName {
    constructor(
        public use?: string,
        public text?: string,
        public family?: string,
        public given?: string[],
        public prefix?: string[],
        public suffix?: string[]
    ) {}}

    export class Identifier {
    constructor(public value: string = "") {}}

    export class ContactPoint {
    constructor() {}}

    export class Address {
        constructor(
            public use?: string,
            public type?: string,
            public text?: string,
            public city?: string
        ) {}
    }

    export class CodeableConcept {
        constructor(
            public coding?: Array<Coding>, 
            public text?: string
        ) {}
    }

    export class StatusCode {
        constructor(public value: string = "") {}
    }

    export class Coding {
    constructor(
        public system?: string,
        public code?: string,
        public display?: string
    ) {}}

    export class Period {
    constructor(public start?: Date, public end?: Date) {}}

    export class Reference {
    constructor(
        public reference?: string,
        public type?: string,
        public identifier?: Identifier,
        public display?: string
    ) {}
}
