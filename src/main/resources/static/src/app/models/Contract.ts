export class Contract {
  constructor(
    public id: string = "",
    public text?: Array<Narrative>,
    public version: string = "",
    public status: string = "",
    public contentDefinition?: Array<ContentDefinition>
  ) {}
}

export class Narrative {
  constructor(
    public status: string = "",
    public div: string = ""
  ) {}
}
export class ContentDefinition {
  constructor(
    public type: Array<CodableConcept>,
    public publicationDate: Date
  ) {}
}
export class CodableConcept{
    constructor(
        public coding: string = "",
        public text: string = ""
    ){}
}
